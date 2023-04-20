package racingcar.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.web.controller.dto.GameInformationDto;
import racingcar.web.controller.dto.GameResultDto;
import racingcar.web.service.RacingCarService;

import java.util.List;

@RestController
public class RacingCarController {

    private RacingCarService racingCarService;

    public RacingCarController() {
    }

    @Autowired
    public RacingCarController(RacingCarService racingCarService) {
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
