package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.view.util.TextParser;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarController {

    private final RacingCarDao racingCarDao;

    public RacingCarController(final RacingCarDao racingCarDao) {
        this.racingCarDao = racingCarDao;
    }

    @Transactional
    @PostMapping("/plays")
    public ResponseEntity<GameResultDto> play(@RequestBody PlayRequestDto playRequestDto) {
        RacingGame racingGame = createGame(playRequestDto.getNames());
        int count = playRequestDto.getCount();
        racingGame.race(count);

        String winners = String.join(", ", racingGame.getWinnerNames());
        final List<Car> cars = racingGame.getCars();

        final long resultId = racingCarDao.saveWinners(count, winners);
        racingCarDao.saveCars(resultId, cars);

        final List<CarDto> carsDto = cars.stream().map(CarDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(new GameResultDto(carsDto, winners));
    }

    private static RacingGame createGame(final String rawCarNames) {
        List<String> carNames = TextParser.parseByDelimiter(rawCarNames, ",");
        return RacingGame.of(carNames);
    }
}
