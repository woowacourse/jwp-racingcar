package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameInformationDto;
import racingcar.controller.dto.RacingCarDto;
import racingcar.dao.GameRecordDao;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;
import racingcar.domain.*;
import racingcar.controller.dto.GameResultDto;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

@Service
public class RacingCarService {

    private ResultDao resultDao;
    private RacingCarDao racingCarDao;
    private GameRecordDao gameRecordDao;


    public RacingCarService() {
    }

    @Autowired
    public RacingCarService(ResultDao resultDao, RacingCarDao racingCarDao, GameRecordDao gameRecordDao) {
        this.resultDao = resultDao;
        this.racingCarDao = racingCarDao;
        this.gameRecordDao = gameRecordDao;
    }

    public GameResultDto runGame(GameInformationDto gameInformationDto) {
        Cars cars = makeCars(gameInformationDto.getNames());
        int trialCount = gameInformationDto.getCount();

        NumberGenerator numberGenerator = new RandomNumberGenerator();
        playRacing(cars, trialCount, numberGenerator);

        long resultId = saveGameResult(cars, trialCount);

        for (Car car : cars.getCars()) {
            saveRacingCars(resultId, car);
        }

        return new GameResultDto(cars.getWinnerCars(), makeRacingCarsDto(cars));
    }

    public Cars makeCars(String carNames) {
        return new Cars(carNames);
    }

    public void playRacing(Cars cars, int trialCount, NumberGenerator numberGenerator) {
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

    public ResultDao getResultDao() {
        return resultDao;
    }

    public RacingCarDao getRacingCarDao() {
        return racingCarDao;
    }
}
