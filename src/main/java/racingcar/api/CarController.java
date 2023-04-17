package racingcar.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameDto;
import racingcar.dto.GameResponse;
import racingcar.dto.WinnerCarDto;
import racingcar.service.CarService;

import java.util.List;

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

    @GetMapping("/plays")
    public List<GameResponse> getPlaysHistory() {
        return carService.findPlaysHistory();
    }
}
