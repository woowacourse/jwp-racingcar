package racingcar.service;

import racingcar.domain.Cars;
import racingcar.genertor.NumberGenerator;

public class GamePlay {

    private GamePlay() {
        throw new AssertionError("인스턴스화할 수 없습니다");
    }

    public static void play(Cars cars, int count, NumberGenerator numberGenerator) {
        while (count-- > 0) {
            cars.moveCars(numberGenerator);
        }
    }
}
