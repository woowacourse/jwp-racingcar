package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.domain.GameManager;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.*;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RacingCarController {

    public static final String SEPARATOR = ",";

    @PostMapping("/plays")
    public ResponseEntity<ResultResponse> play(@RequestBody final NamesAndCountRequest namesAndCount) {
        List<String> carNames = List.of(namesAndCount.getNames().split(SEPARATOR));
        CarNamesRequest carNamesRequest = new CarNamesRequest(carNames);
        GameManager gameManager = new GameManager(new RandomNumberGenerator());
        gameManager.createCars(carNamesRequest);

        GameRoundRequest gameRoundRequest = new GameRoundRequest(namesAndCount.getCount());
        gameManager.createGameRound(gameRoundRequest);

        List<CarStatusResponse> carStatusResponses = new ArrayList<>();
        while (!gameManager.isEnd()) {
            carStatusResponses = gameManager.playGameRound();
        }

        List<RacingCarsResponse> racingCarsResponses = new ArrayList<>();

        for (CarStatusResponse carStatusRespons : carStatusResponses) {
            racingCarsResponses.add(new RacingCarsResponse(carStatusRespons.getCarName(), carStatusRespons.getCarPosition()));
        }

        GameResultResponse gameResultResponse = gameManager.decideWinner();

        ResultResponse resultResponse = new ResultResponse(String.join(",", gameResultResponse.getWinnerNames()), racingCarsResponses);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resultResponse);
    }
}
