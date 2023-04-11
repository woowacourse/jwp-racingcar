package racingcar.domain.race;

import java.util.List;
import racingcar.domain.car.Car;

public interface WinnerJudge {
    List<Car> getWinner(List<Car> cars);
}
