package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.domain.GameManager;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RacingCarController {

    public static final String SEPARATOR = ",";

    @PostMapping("/plays")
    public ResponseEntity<ResultResponse> play(@RequestBody final NamesAndCountRequest namesAndCount) {
        GameManager gameManager = new GameManager(new RandomNumberGenerator());

        createCars(namesAndCount, gameManager);
        createCount(namesAndCount, gameManager);

        List<CarStatusResponse> carStatusResponses = new ArrayList<>();
        while (!gameManager.isEnd()) {
            carStatusResponses = gameManager.playGameRound();
        }

        List<RacingCarResponse> racingCarResponses = convertRacingCars(carStatusResponses);
        String winners = convertWinners(gameManager);

        ResultResponse resultResponse = new ResultResponse(winners, racingCarResponses);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resultResponse);
    }

    private String convertWinners(final GameManager gameManager) {
        GameResultResponse gameResultResponse = gameManager.decideWinner();
        return String.join(",", gameResultResponse.getWinnerNames());
    }

    private List<RacingCarResponse> convertRacingCars(final List<CarStatusResponse> carStatusResponses) {
        List<RacingCarResponse> racingCarsResponses = new ArrayList<>();
        for (CarStatusResponse carStatusResponse : carStatusResponses) {
            racingCarsResponses.add(new RacingCarResponse(carStatusResponse.getCarName(), carStatusResponse.getCarPosition()));
        }
        return racingCarsResponses;
    }

    private void createCount(final NamesAndCountRequest namesAndCount, final GameManager gameManager) {
        GameRoundRequest gameRoundRequest = new GameRoundRequest(namesAndCount.getCount());
        gameManager.createGameRound(gameRoundRequest);
    }

    private void createCars(final NamesAndCountRequest namesAndCount, final GameManager gameManager) {
        List<String> carNames = List.of(namesAndCount.getNames().split(SEPARATOR));
        CarNamesRequest carNamesRequest = new CarNamesRequest(carNames);
        gameManager.createCars(carNamesRequest);
    }
}
