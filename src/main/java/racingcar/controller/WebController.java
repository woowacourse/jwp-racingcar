package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameInformationDto;
import racingcar.controller.dto.GameResultDto;
import racingcar.service.RacingCarService;

import java.util.List;

@RestController
public class WebController {

    private RacingCarService racingCarService;

    @Autowired
    public WebController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("plays")
    public GameResultDto createGame(@RequestBody GameInformationDto gameInformationDto) {
        return racingCarService.runGame(gameInformationDto);
    }

    @GetMapping("plays")
    public List<GameResultDto> createGameResult() {
        return racingCarService.findGameRecord();
    }
}
