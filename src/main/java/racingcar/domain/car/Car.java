package racingcar.domain.car;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import racingcar.domain.strategy.move.MoveStrategy;

@EqualsAndHashCode
@Getter
@ToString
public class Car {
    private final Name name;
    private final Position position;
    
    public Car(final Name name) {
        this(name, new Position(0));
    }
    
    private Car(final Name name, final Position position) {
        this.name = name;
        this.position = position;
    }
    
    public Car move(final MoveStrategy moveStrategy) {
        if (moveStrategy.isMovable()) {
            return new Car(this.name, this.position.increase());
        }
        
        return this;
    }
}
