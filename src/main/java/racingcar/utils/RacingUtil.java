package racingcar.utils;

import org.springframework.stereotype.Component;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.ResultDto;
import racingcar.vo.Name;
import racingcar.vo.Names;
import racingcar.vo.Trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RacingUtil {

    private final NumberGenerator numberGenerator;

    public RacingUtil(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public ResultDto race(String names, int count) {
        Cars cars = initializeCars(names);
        playGame(cars, Trial.of(count));
        return new ResultDto(cars.getWinnerNames(), cars.getCarDtos());
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
}
