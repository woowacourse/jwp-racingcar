package racingcar.model.car.strategy;

import org.springframework.stereotype.Component;

@Component
public interface MovingStrategy {
    boolean movable();
}
