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
import racingcar.service.PlayerService;
import racingcar.service.RecordService;

@RestController
public class WebController {

    private final PlayerService playerService;
    private final GameService gameService;
    private final RecordService recordService;

    @Autowired
    public WebController(final PlayerService playerService, final GameService gameService, final RecordService recordService) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.recordService = recordService;
    }

    @PostMapping("/plays")
    @Transactional
    public PlayResponse plays(@RequestBody final PlayRequest playRequest) {
        Cars cars = playerService.createCars(playRequest);
        playerService.saveCars(cars);

        long gameId = gameService.saveGame(playRequest);
        gameService.play(playRequest, cars);

        return recordService.result(gameId, cars);
    }
}
