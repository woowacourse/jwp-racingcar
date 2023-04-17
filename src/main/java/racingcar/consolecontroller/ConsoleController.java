package racingcar.consolecontroller;

import racingcar.dto.GameResultDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Arrays;

public class ConsoleController {

    private final RacingGameService racingGameService;
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleController(
            final RacingGameService racingGameService,
            final InputView inputView,
            final OutputView outputView
    ) {
        this.racingGameService = racingGameService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String[] names = inputView.inputCarName();
        String gameTime = inputView.inputGameTime();

        final GameResultDto play = racingGameService.play(Arrays.asList(names), Integer.parseInt(gameTime));
        outputView.printWinners(play.getWinners());
        play.getRacingCars()
                .forEach(car -> outputView.printPosition(car.getName(), car.getPosition()));
    }
}
