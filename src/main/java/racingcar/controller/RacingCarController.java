package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.view.util.TextParser;

@RestController
public class RacingCarController {

    @PostMapping("/plays")
    public ResponseEntity<GameResultDto> play(@RequestBody PlayRequestDto playRequestDto) {
        RacingGame racingGame = createGame(playRequestDto);

        List<Car> cars = racingGame.getCars();
        String winners = String.join(", ", racingGame.getWinnerNames());

        return ResponseEntity.ok(new GameResultDto(winners, cars));
    }

    private RacingGame createGame(PlayRequestDto playRequestDto) {
        List<String> carNames = TextParser.parseByDelimiter(playRequestDto.getNames(), ",");
        int count = playRequestDto.getCount();

        RacingGame racingGame = new RacingGame();
        racingGame.enrollCars(carNames);

        for (int i = 0; i < count; i++) {
            racingGame.race();
        }

        return racingGame;
    }
}
