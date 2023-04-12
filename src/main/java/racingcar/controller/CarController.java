package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.GameInfo;
import racingcar.dto.WinnerCarDto;
import racingcar.service.CarService;
import racingcar.utils.RacingRandomNumberGenerator;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(PlayerDao playerDao, PlayResultDao playResultDao) {
        this.carService = new CarService(new RacingRandomNumberGenerator(), playerDao, playResultDao);
    }

    @PostMapping("/plays")
    public WinnerCarDto playGame(@RequestBody GameInfo gameInfo) {
        return carService.playGame(gameInfo);
    }

}
