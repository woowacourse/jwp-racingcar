package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.model.Cars;
import racingcar.service.GameService;
import racingcar.service.RecordService;

@RestController
public class WebController {

    private final GameService gameService;
    private final RecordService recordService;

    @Autowired
    public WebController(final GameService gameService, final RecordService recordService) {
        this.gameService = gameService;
        this.recordService = recordService;
    }

    @PostMapping("/plays")
    @Transactional
    public PlayResponse plays(@RequestBody final PlayRequest playRequest) {
        Cars cars = gameService.createCars(playRequest);

        long gameId = gameService.saveGame(playRequest);
        gameService.play(playRequest, cars);
        recordService.saveResults(gameId, cars);

        return recordService.result(cars);
    }
}
