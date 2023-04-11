package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.GameInfoDto;
import racingcar.dto.ResultDto;
import racingcar.services.GameService;

@RestController
public class WebController {

    @Autowired
    private final GameService gameService;

    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResultDto play(@RequestBody GameInfoDto gameInfoDto) {
        gameService.initialize();
        gameService.createCars(gameInfoDto.getNames());
        gameService.moveCars(gameInfoDto.getCount());
        return new ResultDto(gameService.getWinner(), gameService.getResult());
    }

}
