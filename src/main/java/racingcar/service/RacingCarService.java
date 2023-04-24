package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.RacingCarData;
import racingcar.dto.RacingCarResponse;
import racingcar.dto.RacingGameRequest;
import racingcar.entity.RacingCar;
import racingcar.entity.Result;
import racingcar.repository.RacingCarDao;
import racingcar.repository.ResultDao;
import racingcar.util.NumberGenerator;
import racingcar.validation.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private ResultDao resultDao;
    private RacingCarDao racingCarDao;

    @Autowired
    public RacingCarService(ResultDao resultDao, RacingCarDao racingCarDao) {
        this.resultDao = resultDao;
        this.racingCarDao = racingCarDao;
    }

    public RacingCarService(){}

    public RacingCarResponse play(RacingGameRequest racingGameRequest, NumberGenerator numberGenerator) {
        Cars cars = getCars(racingGameRequest.getNames());
        int trialCount = getTrialCount(racingGameRequest.getCount());

        List<RacingCarData> racingCars = playGame(cars, trialCount, numberGenerator);
        insertGame(trialCount, cars);

        return new RacingCarResponse(cars.getWinnerCars(), racingCars);
    }

    public Cars getCars(String names) {
        Validation.validateCarNames(names);
        return new Cars(names);
    }

    public int getTrialCount(int trialCount) {
        Validation.validateTryCount(trialCount);
        return trialCount;
    }

    public List<RacingCarData> playGame(Cars cars, int trialCount, NumberGenerator numberGenerator) {
        for (int count = 0; count < trialCount; count++) {
            cars.moveForRound(numberGenerator);
        }

        List<RacingCarData> racingCars = new ArrayList<>();
        for (Car car : cars.getCars()) {
            RacingCarData racingCarData = new RacingCarData(car.getName(), car.getLocation());
            racingCars.add(racingCarData);
        }
        return racingCars;
    }

    private void insertGame(int trialCount, Cars cars) {
        long resultId = resultDao.insert(trialCount, cars.getWinnerCars());

        for (Car car : cars.getCars()) {
            racingCarDao.insert(car, resultId);
        }
    }

    public List<RacingCarResponse> findAllGame() {
        List<Result> results = resultDao.findAll();

        List<RacingCarResponse> gameResults = new ArrayList<>();

        for (Result result : results) {
            gameResults.add(getRacingCarData(result));
        }

        return gameResults;
    }

    private RacingCarResponse getRacingCarData(Result result) {
        String winners = result.getWinners();
        List<RacingCar> racingCars = racingCarDao.findBy(result.getId());
        List<RacingCarData> racingCarDatas =
                racingCars.stream()
                          .map(racingCar ->
                                  new RacingCarData(racingCar.getName(), racingCar.getPosition()))
                          .collect(Collectors.toList());

        return new RacingCarResponse(winners, racingCarDatas);
    }
}
