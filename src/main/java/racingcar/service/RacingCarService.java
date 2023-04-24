package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.GameInforamtionDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.RacingCarDto;
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

    public GameResultDto play(GameInforamtionDto gameInforamtionDto, NumberGenerator numberGenerator) {
        Cars cars = getCars(gameInforamtionDto.getNames());
        int trialCount = getTrialCount(gameInforamtionDto.getCount());

        List<RacingCarDto> racingCars = playGame(cars, trialCount, numberGenerator);
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

    public List<RacingCarDto> playGame(Cars cars, int trialCount, NumberGenerator numberGenerator) {
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
        List<Result> results = resultDao.findAll();

        List<GameResultDto> gameResults = new ArrayList<>();

        for (Result result : results) {
            gameResults.add(getRacingCarDto(result));
        }

        return gameResults;
    }

    private GameResultDto getRacingCarDto(Result result) {
        String winners = result.getWinners();
        List<RacingCar> racingCars = racingCarDao.findBy(result.getId());
        List<RacingCarDto> racingCarDtos =
                racingCars.stream()
                          .map(racingCar ->
                                  new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                          .collect(Collectors.toList());

        return new GameResultDto(winners, racingCarDtos);
    }
}
