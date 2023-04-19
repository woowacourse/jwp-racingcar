package racingcar;

import racingcar.controller.GamePlay;
import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;
import racingcar.view.InputView;

public class Main {
    public static void main(String[] args) {
        Cars cars = new Cars(CarFactory.buildCars(InputView.inputCarNames()));
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        GamePlay gamePlay = new GamePlay(cars, numberGenerator);
        gamePlay.gameStart();
    }
}
