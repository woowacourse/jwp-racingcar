package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResultDto;
import racingcar.view.util.TextParser;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RacingCarService {

    private final RacingCarDao racingCarDao;

    public RacingCarService(final RacingCarDao racingCarDao) {
        this.racingCarDao = racingCarDao;
    }

    @Transactional
    public PlayResultDto play(final PlayRequestDto request) {
        final RacingGame racingGame = createGame(request.getNames());
        racingGame.race(request.getCount());

        saveGame(request.getCount(), racingGame);

        return makePlayResult(racingGame);
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

    public List<GameResultDto> findAllResult() {
        return racingCarDao.findAllResult();
    }

    public List<Car> findCarsByResultId(final Long id) {
        return racingCarDao.findCarsByResultId(id);
    }
}

