package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.RacingCarDao;
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
        final RacingGame racingGame = createGame(playRequestDto.getNames());
        final int count = playRequestDto.getCount();
        racingGame.race(count);

        saveGame(count, racingGame);

        return makeGameResultDto(racingGame);
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

    private ResponseEntity<GameResultDto> makeGameResultDto(final RacingGame racingGame) {
        final List<CarDto> carsDto = racingGame.getCars().stream()
                .map(CarDto::new)
                .collect(Collectors.toList());
        final String winners = String.join(", ", racingGame.getWinnerNames());

        return ResponseEntity.ok(new GameResultDto(carsDto, winners));
    }
}
