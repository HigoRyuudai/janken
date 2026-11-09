
package oit.is.z3680.kaizi.janken;

import java.io.IOException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z3680.kaizi.janken.mapper.MatchMapper;
import oit.is.z3680.kaizi.janken.model.Match;

@Component
public class AsyncKekka {

  private final MatchMapper matchMapper;

  public AsyncKekka(MatchMapper matchMapper) {
    this.matchMapper = matchMapper;
  }

  @Async
  public void asyncShowMatch(SseEmitter emitter, long userId) {

    boolean dbUpdated = false;

    while (!dbUpdated) {

      Match match = matchMapper.selectActiveMatchByUserId(userId);

      if (match != null) {
        try {
          // 対戦結果をブラウザに送信
          emitter.send(SseEmitter.event()
              .name("kekka")
              .data(match));

          dbUpdated = true;

          // 結果を送信した後、DBの試合を非アクティブにする
          matchMapper.updateActiveFalse(match.getId());

          emitter.complete();

        } catch (IOException e) {
          emitter.completeWithError(e);
          return;
        }

      } else {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          emitter.completeWithError(e);
          return;
        }
      }
    }
  }
}
