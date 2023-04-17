package racingcar;

import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.controller.GameController;
import racingcar.dao.JdbcCarDao;
import racingcar.dao.JdbcGameDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class Application {

    private static InputView createInputView() {
        return new InputView(new Scanner(System.in), new OutputView());
    }

    public static void main(String[] args) {
        GameController gameController = new GameController(createInputView(), new OutputView());
        gameController.run();
    }
}
