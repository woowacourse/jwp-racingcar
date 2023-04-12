package racingcar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class RacingController {

    @PostMapping(path = "/plays")
    @ResponseBody
    public void playRacingGame(
            @Valid @RequestBody RacingGameRequestDto racingGameRequestDto
    ) {
    }
}
