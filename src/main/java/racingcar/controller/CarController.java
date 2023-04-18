package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.service.CarService;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(final CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/plays")
    public GamePlayResponseDto playGame(@RequestBody GamePlayRequestDto gamePlayRequestDto) {
        return carService.playGame(gamePlayRequestDto);
    }

}
