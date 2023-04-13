package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.GameInfoDto;
import racingcar.dto.ResultDto;
import racingcar.model.car.Cars;
import racingcar.services.GameService;

@RestController
public class WebController {

    private final GameService gameService;

    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultDto> play(@RequestBody GameInfoDto gameInfoDto) {
        try {
            Cars cars = gameService.initialize();
            gameService.createCars(cars, gameInfoDto.getNames());
            gameService.moveCars(cars, gameInfoDto.getCount());
            ResultDto resultDto = new ResultDto(gameService.getWinner(cars), gameService.getResult(cars));
            gameService.saveResult(gameInfoDto.getCount(), resultDto);
            return ResponseEntity.ok().body(resultDto);
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
