package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.model.Cars;
import racingcar.util.NameFormatConverter;
import racingcar.util.NumberGenerator;

@Service
public class GameService {

    private final NumberGenerator numberGenerator;
    private final GameDao gameDao;
    private final CarDao carDao;

    @Autowired
    public GameService(NumberGenerator numberGenerator, CarDao carDao, GameDao gameDao) {
        this.numberGenerator = numberGenerator;
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public RacingGameResponseDto executeRacingGame(RacingGameRequestDto racingGameRequestDto) {
        int tryCount = racingGameRequestDto.getCount();
        Cars cars = new Cars(NameFormatConverter.splitNameByDelimiter(racingGameRequestDto.getNames()));

        for (int count = 0; count < tryCount; count++) {
            cars.moveResult(numberGenerator);
        }

        String winners = NameFormatConverter.joinNameWithDelimiter(cars.getWinners());
        int gameId = gameDao.save(tryCount, winners);
        carDao.saveAll(gameId, cars.getCars());

        return new RacingGameResponseDto(cars.getWinners(), cars.getCars());
    }
}
