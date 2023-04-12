package racingcar.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.carfactory.CarFactory;
import racingcar.domain.cars.Cars;
import racingcar.domain.record.GameRecorder;
import racingcar.domain.system.GameSystem;
import racingcar.dto.RequestDTO;
import racingcar.dto.ResponseDTO;
import racingcar.service.RacingCarService;

@RestController
public class WebController {

    private final RacingCarService racingCarService;

    public WebController() {
        this.racingCarService = new RacingCarService();
    }

    @PostMapping("/plays")
    public ResponseEntity createGame(@RequestBody RequestDTO requestDTO) {
        final String names = requestDTO.getNames();
        final int count = requestDTO.getCount();
        ResponseDTO responseDTO = racingCarService.play(names, count);
        return ResponseEntity.ok().body(responseDTO);
    }

    private Cars makeCars(final List<String> carNames) {
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(carNames);
    }

    private GameSystem createGameSystem(final int gameRound) {
        return new GameSystem(gameRound, new GameRecorder(new ArrayList<>()));
    }
}