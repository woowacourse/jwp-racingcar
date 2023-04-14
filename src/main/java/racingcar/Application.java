package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.domain.GameProcess;
import racingcar.domain.movingstrategy.DefaultMovingStrategy;
import racingcar.view.IOViewResolver;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public final class Application {

    private static final GameProcess INITIAL_STATUS = GameProcess.READ_CAR_NAMES;

    public static void main(String[] args) {
        IOViewResolver ioViewResolver = new IOViewResolver(InputView.getInstance(), OutputView.getInstance());
        RacingGameController controller = new RacingGameController(ioViewResolver, new DefaultMovingStrategy());

        GameProcess process = INITIAL_STATUS;
        while (process != GameProcess.EXIT) {
            process = controller.run(process);
        }
    }
}
