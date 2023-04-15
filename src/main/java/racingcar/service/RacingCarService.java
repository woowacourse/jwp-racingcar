package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.PlayResultDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
import racingcar.view.util.TextParser;

@Service
public class RacingCarService {

    private final PlayResultDao playResultDao;
    private final CarDao carDao;

    public RacingCarService(final PlayResultDao playResultDao, final CarDao carDao) {
        this.playResultDao = playResultDao;
        this.carDao = carDao;
    }

    @Transactional
    public PlayResponseDto playGame(PlayRequestDto playRequest) {
        final RacingGame racingGame = createGame(playRequest.getNames());
        final int count = playRequest.getCount();

        race(count, racingGame);
        final List<Car> cars = racingGame.getCars();
        final List<String> winnerNames = racingGame.getWinnerNames();

        saveGame(count, CarDtoBuilder.dtos(cars, winnerNames));
        return new PlayResponseDto(CarDtoBuilder.responseDtos(cars), String.join(", ", winnerNames));
    }

    private RacingGame createGame(final String rawCarNames) {
        final List<String> carNames = TextParser.parseByDelimiter(rawCarNames, ",");
        return RacingGame.of(carNames);
    }

    private void race(final int count, final RacingGame racingGame) {
        for (int i = 0; i < count; i++) {
            racingGame.race();
        }
    }

    private void saveGame(int count, List<CarDto> cars) {
        long savedId = playResultDao.insertAndReturnId(count);
        carDao.insert(savedId, cars);
    }

}
