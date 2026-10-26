
package oit.is.z3680.kaizi.janken.controller;

import oit.is.z3680.kaizi.janken.mapper.UserMapper;
import oit.is.z3680.kaizi.janken.model.Entry;
import oit.is.z3680.kaizi.janken.model.janken;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import oit.is.z3680.kaizi.janken.model.User;
import oit.is.z3680.kaizi.janken.mapper.MatchMapper;
import oit.is.z3680.kaizi.janken.model.Match;
import java.util.Random;

@Controller
public class JankenController {

  private final Entry entry;
  private final UserMapper userMapper;
  private final MatchMapper matchMapper;

  // EntryをDIで受け取る
  public JankenController(Entry entry, UserMapper userMapper, MatchMapper matchMapper) {
    this.entry = entry;
    this.userMapper = userMapper;
    this.matchMapper = matchMapper;
  }

  @GetMapping("/janken")
  public String janken(Authentication authentication, ModelMap model) {
    String username = authentication.getName();

    entry.addUser(username);

    ArrayList<User> users = userMapper.selectAll();
    ArrayList<Match> matches = matchMapper.selectAll();

    model.addAttribute("username", username);
    model.addAttribute("users", users);
    model.addAttribute("matches", matches);

    return "janken.html";
  }

  @PostMapping("/janken")
  public String jankenPost(
      @RequestParam String username,
      ModelMap model) {
    model.addAttribute("username", username);
    return "janken.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam("id") long id,
      Authentication authentication,
      ModelMap model) {
    String username = authentication.getName();
    User opponent = userMapper.selectById(id);

    model.addAttribute("username", username);
    model.addAttribute("opponent", opponent);

    return "match.html";
  }

  @GetMapping("/jankengame")
  public String jankengame(
      @RequestParam String hand,
      Authentication authentication,
      ModelMap model) {

    String username = authentication.getName();
    entry.addUser(username);

    String myHand = hand;
    String cpuHand = "グー";
    String result;

    if (myHand.equals(cpuHand)) {
      result = "あいこ";
    } else if (myHand.equals("チョキ")) {
      result = "負け";
    } else {
      result = "勝ち";
    }

    janken janken = new janken(myHand, cpuHand, result);

    model.addAttribute("username", username);
    model.addAttribute("users", entry.getUsers());
    model.addAttribute("janken", janken);

    return "janken.html";
  }

  @GetMapping("/fight")
  public String fight(
      @RequestParam("id") long opponentId,
      @RequestParam("hand") String hand,
      Authentication authentication,
      ModelMap model) {

    String username = authentication.getName();

    // 自分と対戦相手の情報を取得
    User me = userMapper.selectByName(username);
    User opponent = userMapper.selectById(opponentId);

    if (me == null || opponent == null) {
      return "redirect:/janken";
    }

    // 選択した手を確認
    if (!hand.equals("Gu") &&
        !hand.equals("Choki") &&
        !hand.equals("Pa")) {
      return "redirect:/match?id=" + opponentId;
    }

    // CPUの手をランダムに決定
    String[] hands = { "Gu", "Choki", "Pa" };
    String cpuHand = hands[new Random().nextInt(hands.length)];

    // 勝敗判定
    String result;

    if (hand.equals(cpuHand)) {
      result = "あいこ";
    } else if ((hand.equals("Gu") && cpuHand.equals("Choki")) ||
        (hand.equals("Choki") && cpuHand.equals("Pa")) ||
        (hand.equals("Pa") && cpuHand.equals("Gu"))) {
      result = "勝ち";
    } else {
      result = "負け";
    }

    // 対戦結果をデータベースに登録
    Match match = new Match();
    match.setUser1(me.getId());
    match.setUser2(opponent.getId());
    match.setUser1Hand(hand);
    match.setUser2Hand(cpuHand);

    matchMapper.insertMatch(match);

    // 結果を画面に渡す
    model.addAttribute("username", username);
    model.addAttribute("opponent", opponent);
    model.addAttribute("myHand", hand);
    model.addAttribute("opponentHand", cpuHand);
    model.addAttribute("result", result);

    return "match.html";
  }
}
