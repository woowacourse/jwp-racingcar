package racingcar.controller.console;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.view.OutputView;

@ControllerAdvice(basePackageClasses = ConsoleRacingGameController.class)
public class ConsoleRacingGameControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(Exception exception) {
        OutputView.printErrorMessage(exception.getMessage());
    }
}
