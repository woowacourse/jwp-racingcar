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

    private final ResultDao resultDao;
    private final RacingCarDao racingCarDao;
    private final NumberGenerator numberGenerator;

    public RacingCarService(ResultDao resultDao, RacingCarDao racingCarDao, NumberGenerator numberGenerator) {
        this.resultDao = resultDao;
        this.racingCarDao = racingCarDao;
        this.numberGenerator = numberGenerator;
    }

    public GameResultDto play(GameInforamtionDto gameInforamtionDto) {
        Cars cars = getCars(gameInforamtionDto);
        int trialCount = getTrialCount(gameInforamtionDto);

        List<RacingCarDto> racingCars = playGame(cars, trialCount);
        insertGame(trialCount, cars);

        return new GameResultDto(cars.getWinnerCars(), racingCars);
    }

    private Cars getCars(GameInforamtionDto gameInforamtionDto) {
        String names = gameInforamtionDto.getNames();
        Validation.validateCarNames(names);
        return new Cars(names);
    }

    private int getTrialCount(GameInforamtionDto gameInforamtionDto) {
        int trialCount = gameInforamtionDto.getCount();
        Validation.validateTryCount(trialCount);
        return trialCount;
    }

    private List<RacingCarDto> playGame(Cars cars, int trialCount) {
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
}
