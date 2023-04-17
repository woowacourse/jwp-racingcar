package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.domain.GameProcess;
import racingcar.utils.DefaultMovingStrategy;
import racingcar.view.IOViewResolver;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Manager {
    private static final GameProcess INITIAL_STATUS = GameProcess.READ_CAR_NAMES;

    private Manager() {
        throw new AssertionError();
    }

    public static void run() {
        IOViewResolver ioViewResolver = new IOViewResolver(InputView.getInstance(), OutputView.getInstance());
        RacingGameController controller = new RacingGameController(ioViewResolver, new DefaultMovingStrategy());

        GameProcess process = INITIAL_STATUS;
        while (process != GameProcess.EXIT) {
            process = controller.run(process);
        }
    }
}
