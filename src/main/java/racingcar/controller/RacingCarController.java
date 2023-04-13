package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.CarDao;
import racingcar.dao.PlayResultDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.view.util.TextParser;

@RestController
public class RacingCarController {

    private final PlayResultDao playResultDao;
    private final CarDao carDao;

    public RacingCarController(final PlayResultDao playResultDao, final CarDao carDao) {
        this.playResultDao = playResultDao;
        this.carDao = carDao;
    }

    @Transactional
    @PostMapping("/plays")
    public ResponseEntity<GameResultDto> play(@RequestBody PlayRequestDto playRequestDto) {
        RacingGame racingGame = createGame(playRequestDto.getNames());
        int count = playRequestDto.getCount();

        race(count, racingGame);
        long savedId = playResultDao.insert(count);

        List<Car> cars = racingGame.getCars();
        String winners = String.join(", ", racingGame.getWinnerNames());
        saveResult(savedId, cars, winners);

        return ResponseEntity.ok(new GameResultDto(cars, winners));
    }

    private static RacingGame createGame(final String rawCarNames) {
        List<String> carNames = TextParser.parseByDelimiter(rawCarNames, ",");
        return RacingGame.of(carNames);
    }

    private void race(final int count, final RacingGame racingGame) {
        for (int i = 0; i < count; i++) {
            racingGame.race();
        }
    }

    private void saveResult(final long savedId, final List<Car> cars, final String winners) {
        carDao.save(savedId, cars);
        playResultDao.update(savedId, winners);
    }

}
