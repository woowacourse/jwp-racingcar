package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameDto;
import racingcar.dto.GameResponse;
import racingcar.dto.WinnerCarDto;
import racingcar.service.CarService;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(final CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/plays")
    public GameResponse playGame(@RequestBody final GameDto gameDto) {
        final WinnerCarDto winnerCarDto = carService.playGame(gameDto);
        return new GameResponse(winnerCarDto.joinWinnerNames(), winnerCarDto.getRacingCars());
    }

}
