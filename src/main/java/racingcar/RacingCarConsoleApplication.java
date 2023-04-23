package racingcar;

import racingcar.controller.RacingGameConsoleController;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(RacingGameService.generateDefaultRacingGameService(), new InputView(new Parser(), new Scanner(System.in)), new OutputView());
        racingGameConsoleController.run();
    }
}
