package oit.is.z3680.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3680.kaizi.janken.model.janken;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String janken() {
    return "janken.html";
  }

  @PostMapping("/janken")
  public String jankenPost(@RequestParam String username, ModelMap model) {
    model.addAttribute("username", username);
    return "janken.html";
  }

  @GetMapping("/jankengame")
  public String jankengame(@RequestParam String hand, ModelMap model) {
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
    model.addAttribute("janken", janken);

    return "janken.html";
  }
}
