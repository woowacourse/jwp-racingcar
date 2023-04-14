package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.CarDto;
import racingcar.dto.CarInfo;
import racingcar.dto.ResultDto;
import racingcar.utils.NumberGenerator;
import racingcar.vo.Name;
import racingcar.vo.Names;
import racingcar.vo.Trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class RacingService {

    private final NumberGenerator numberGenerator;
    private final RacingDao racingDao;

    public RacingService(NumberGenerator numberGenerator, RacingDao racingDao) {
        this.numberGenerator = numberGenerator;
        this.racingDao = racingDao;
    }

    public ResultDto race(String names, int count) {
        Cars cars = initializeCars(names);
        Trial trial = Trial.of(count);
        playGame(cars, trial);
        return new ResultDto(trial, cars.getWinnerNames(), cars.getCarDtos());
    }

    private Cars initializeCars(String names) {
        Names carNames = getCarNames(names);
        return saveCars(carNames);
    }

    private Names getCarNames(String names) {
        return Names.of(
                Name.of(Arrays.asList(names.split(",")))
        );
    }

    private void playGame(Cars cars, Trial trial) {
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
        }
    }

    private Cars saveCars(Names names) {
        List<Car> cars = new ArrayList<>();
        names.nameIterator()
                .forEachRemaining(name -> cars.add(Car.of(name)));
        return new Cars(cars, numberGenerator);
    }

    public void saveResult(ResultDto resultDto) {
        final int racingId = racingDao.insert(resultDto.getTrial());
        for (CarDto car : resultDto.getRacingCars()) {
            String name = car.getName();
            racingDao.insert(new CarInfo(racingId, name, car.getPosition(), resultDto.isWinnerContaining(name)));
        }
    }
}
