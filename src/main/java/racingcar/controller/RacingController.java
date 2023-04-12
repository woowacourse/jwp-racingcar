package racingcar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RacingController {

    @PostMapping(path = "/plays")
    @ResponseBody
    public void playRacingGame(
            @RequestParam("names") String playerNames,
            @RequestParam("count") int tryCount) {
    }
}
