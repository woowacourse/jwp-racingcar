package racingcar.game.model;

import racingcar.car.interfaces.NumberGenerator;

public class FixedNumberGenerator implements NumberGenerator {
    
    @Override
    public int generate() {
        return 5;
    }
}
