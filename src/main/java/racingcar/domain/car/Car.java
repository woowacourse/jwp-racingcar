package racingcar.domain.car;

import lombok.EqualsAndHashCode;
import racingcar.domain.strategy.move.MoveStrategy;

@EqualsAndHashCode
public class Car {
    private final Name name;
    private final Position position;
    
    public Car(Name name, Position position) {
        this.name = name;
        this.position = position;
    }
    
    public Car move(MoveStrategy moveStrategy) {
        if (moveStrategy.isMovable()) {
            return new Car(this.name, this.position.increase());
        }
        
        return this;
    }
}
