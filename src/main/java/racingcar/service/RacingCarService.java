package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameResultDto;
import racingcar.controller.dto.RacingCarDto;
import racingcar.dao.GameRecordDao;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

@Service
public class RacingCarService {

    private final ResultDao resultDao;
    private final RacingCarDao racingCarDao;
    private final GameRecordDao gameRecordDao;

    @Autowired
    public RacingCarService(ResultDao resultDao, RacingCarDao racingCarDao, GameRecordDao gameRecordDao) {
        this.resultDao = resultDao;
        this.racingCarDao = racingCarDao;
        this.gameRecordDao = gameRecordDao;
    }

    public GameResultDto runGame(String carNames, int trialCount) {
        Cars cars = makeCars(carNames);

        NumberGenerator numberGenerator = new RandomNumberGenerator();
        playRacing(cars, trialCount, numberGenerator);

        long resultId = saveGameResult(cars, trialCount);

        for (Car car : cars.getCars()) {
            saveRacingCars(resultId, car);
        }

        return new GameResultDto(cars.getWinnerCars(), makeRacingCarsDto(cars));
    }

    private Cars makeCars(String carNames) {
        return new Cars(carNames);
    }

    private void playRacing(Cars cars, int trialCount, NumberGenerator numberGenerator) {
        for (int count = 0; count < trialCount; count++) {
            cars.moveForRound(numberGenerator);
        }
    }

    private List<RacingCarDto> makeRacingCarsDto(Cars cars) {
        List<RacingCarDto> racingCarsDto = new ArrayList<>();
        for (Car car : cars.getCars()) {
            racingCarsDto.add(new RacingCarDto(car.getName(), car.getLocation()));
        }
        return racingCarsDto;
    }

    private long saveGameResult(Cars cars, int trialCount) {
        return resultDao.insert(trialCount, cars.getWinnerCars());
    }

    private void saveRacingCars(long resultId, Car car) {
        racingCarDao.insert(car, resultId);
    }


    public List<GameResultDto> findGameRecord() {
        return gameRecordDao.findGameRecord();
    }
}
