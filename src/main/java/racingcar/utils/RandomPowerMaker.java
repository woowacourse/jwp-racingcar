package racingcar.utils;

import org.springframework.stereotype.Component;

@Component
public class RandomPowerMaker implements RandomPowerGenerator {

    @Override
    public int generateRandomPower() {
        return (int) (Math.random() * 10);
    }
}
