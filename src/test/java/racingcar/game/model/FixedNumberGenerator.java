package racingcar.game.model;

import racingcar.car.model.NumberGenerator;

public class FixedNumberGenerator implements NumberGenerator {
    
    @Override
    public int generate() {
        return 5;
    }
}
