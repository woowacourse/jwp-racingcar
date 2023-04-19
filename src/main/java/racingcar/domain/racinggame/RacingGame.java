package racingcar.domain.racinggame;

import lombok.Getter;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.move.MoveStrategy;

@Getter
public class RacingGame {
    private final Cars cars;
    private TryNumber tryNumber;
    
    public RacingGame(String names, int tryNUmber) {
        this.cars = new Cars(names);
        this.tryNumber = new TryNumber(tryNUmber);
    }
    
    public void race(MoveStrategy moveStrategy) {
        while (!tryNumber.isFinished()) {
            cars.move(moveStrategy);
            this.tryNumber = tryNumber.decrease();
        }
    }
}
