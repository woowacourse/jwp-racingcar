package racingcar.domain.racinggame;

import lombok.Getter;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.move.MoveStrategy;

@Getter
public class RacingGame {
    private final Cars cars;
    private Count count;
    
    public RacingGame(final String names, final int count) {
        this.cars = new Cars(names);
        this.count = new Count(count);
    }
    
    public void race(final MoveStrategy moveStrategy) {
        while (!count.isFinished()) {
            cars.move(moveStrategy);
            this.count = count.decrease();
        }
    }
}
