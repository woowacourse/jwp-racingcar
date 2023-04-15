package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import java.util.List;
import java.util.stream.IntStream;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;
    private Cars cars;

    public RacingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        initializeCars();
    }

    private void initializeCars() {
        List<String> carNames = inputView.getCarNames();
        this.cars = new Cars(carNames);
    }

    public void race() {
        outputView.resultHeader();
        IntStream.range(0, inputView.getTryCount())
                .forEach(this::repeat);
        outputView.winner(cars.getWinner());
    }

    private void repeat(int count) {
        cars.moveAll();
    }
}
