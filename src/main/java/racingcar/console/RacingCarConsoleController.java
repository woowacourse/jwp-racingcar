package racingcar.console;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.NumberGenerator;
import racingcar.domain.game.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public RacingCarConsoleController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        List<String> carNames = inputView.readCarNames();
        RacingGame racingGame = RacingGame.from(carNames);
        int tryTime = inputView.readTryTime();
        validateTryTime(tryTime);

        racingGame.play(tryTime, numberGenerator);
        printResult(racingGame);
    }

    private void printResult(RacingGame racingGame) {
        List<RacingCar> racingCars = racingGame.getRacingCars();
        List<RacingCar> winners = racingCars.stream().filter(car -> racingGame.isWinner(car))
                .collect(Collectors.toList());

        outputView.printResultTitle();
        outputView.printStatus(racingCars);
        outputView.printWinners(winners);
    }

    private int validateTryTime(int tryTime) {
        if (tryTime < 0) {
            throw new IllegalArgumentException("시도 횟수는 음수일 수 없습니다.");
        }
        return tryTime;
    }
}
