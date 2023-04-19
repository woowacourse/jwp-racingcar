package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.service.CarService;

import java.util.Arrays;
import java.util.List;

@RestController
public class WebCarController {

    private final CarService carService;

    public WebCarController(final CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/plays")
    public GamePlayResponseDto plays(@RequestBody GamePlayRequestDto gamePlayRequestDto) {
        final List<String> carNames = Arrays.asList(gamePlayRequestDto.getNames().split(","));
        return carService.playGame(carNames, gamePlayRequestDto.getCount());
    }

    @GetMapping("/plays")
    public List<GamePlayResponseDto> findGamePlayHistoryAll() {
        return carService.findGamePlayHistoryAll();
    }

}
