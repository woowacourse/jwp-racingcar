package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;
import racingcar.domain.*;
import racingcar.util.NumberGenerator;
import racingcar.validation.Validation;

import java.util.ArrayList;
import java.util.List;

@Service
public class RacingCarService {

    private ResultDao resultDao;
    private RacingCarDao racingCarDao;
    private final NumberGenerator numberGenerator;

    public RacingCarService(ResultDao resultDao, RacingCarDao racingCarDao, NumberGenerator numberGenerator) {
        this.resultDao = resultDao;
        this.racingCarDao = racingCarDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingCarService(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
    }

    public GameResultDto play(GameInforamtionDto gameInforamtionDto) {
        Cars cars = getCars(gameInforamtionDto.getNames());
        int trialCount = getTrialCount(gameInforamtionDto.getCount());

        List<RacingCarDto> racingCars = playGame(cars, trialCount);
        insertGame(trialCount, cars);

        return new GameResultDto(cars.getWinnerCars(), racingCars);
    }

    public Cars getCars(String names) {
        Validation.validateCarNames(names);
        return new Cars(names);
    }

    public int getTrialCount(int trialCount) {
        Validation.validateTryCount(trialCount);
        return trialCount;
    }

    public List<RacingCarDto> playGame(Cars cars, int trialCount) {
        for (int count = 0; count < trialCount; count++) {
            cars.moveForRound(numberGenerator);
        }

        List<RacingCarDto> racingCars = new ArrayList<>();
        for (Car car : cars.getCars()) {
            RacingCarDto racingCarDto = new RacingCarDto(car.getName(), car.getLocation());
            racingCars.add(racingCarDto);
        }
        return racingCars;
    }

    private void insertGame(int trialCount, Cars cars) {
        long resultId = resultDao.insert(trialCount, cars.getWinnerCars());

        for (Car car : cars.getCars()) {
            racingCarDao.insert(car, resultId);
        }
    }

    public List<GameResultDto> findAllGame() {
        List<Long> gameIds = resultDao.findAllId();
        List<GameResultDto> gameResults = new ArrayList<>();

        for (Long gameId : gameIds) {
            String winners = resultDao.findWinnerBy(gameId);
            List<RacingCarDto> racingCars = racingCarDao.findBy(gameId);
            gameResults.add(new GameResultDto(winners, racingCars));
        }

        return gameResults;
    }
}
