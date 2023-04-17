package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.RacingCarDao;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResultDto;
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
    public ResponseEntity<PlayResultDto> play(@RequestBody PlayRequestDto playRequestDto) {
        final RacingGame racingGame = createGame(playRequestDto.getNames());
        final int count = playRequestDto.getCount();
        racingGame.race(count);

        saveGame(count, racingGame);

        return ResponseEntity.ok(makePlayResult(racingGame));
    }

    private static RacingGame createGame(final String rawCarNames) {
        final List<String> carNames = TextParser.parseByDelimiter(rawCarNames, ",");
        return RacingGame.of(carNames);
    }

    private void saveGame(final int count, final RacingGame racingGame) {
        final String winners = String.join(", ", racingGame.getWinnerNames());

        final long resultId = racingCarDao.saveWinners(count, winners);
        racingCarDao.saveCars(resultId, racingGame.getCars());
    }

    private PlayResultDto makePlayResult(final RacingGame racingGame) {
        final List<CarDto> carsDto = racingGame.getCars().stream()
                .map(CarDto::new)
                .collect(Collectors.toList());
        final String winners = String.join(", ", racingGame.getWinnerNames());

        return new PlayResultDto(carsDto, winners);
    }

    @Transactional
    @GetMapping("/plays")
    public ResponseEntity<List<PlayResultDto>> getAllResult() {
        final List<GameResultDto> results = racingCarDao.findAllResult();
        final List<PlayResultDto> allPlayResults = results.stream()
                .map(this::convertToPlayResult)
                .collect(Collectors.toList());

        return ResponseEntity.ok(allPlayResults);
    }

    private PlayResultDto convertToPlayResult(GameResultDto result) {
        final List<CarDto> cars = racingCarDao.findCarsByResultId(result.getId()).stream()
                .map(CarDto::new)
                .collect(Collectors.toList());

        return new PlayResultDto(cars, result.getWinners());
    }
}
