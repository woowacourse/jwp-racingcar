package racingcar.service;

import org.springframework.stereotype.Component;
import racingcar.domain.Cars;
import racingcar.genertor.NumberGenerator;

@Component
public class GamePlay {

    public void play(Cars cars, int count, NumberGenerator numberGenerator) {
        while (count-- > 0) {
            cars.moveCars(numberGenerator);
        }
    }
}
