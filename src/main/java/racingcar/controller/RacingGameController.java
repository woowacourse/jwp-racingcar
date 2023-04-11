package racingcar.controller;

import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

@RestController
public class RacingGameController {

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> play(@RequestBody RacingGameRequest racingGameRequest) {
        Cars cars = new Cars(racingGameRequest.getNames().stream()
                .map(Car::new)
                .collect(Collectors.toList()));
        RacingGame game = new RacingGame(racingGameRequest.getCount(), cars);
        while (!game.isEnd()) {
            game.playOneRound();
        }
        RacingGameResponse response = game.getResult();
        return ResponseEntity.ok(response);
    }
}
