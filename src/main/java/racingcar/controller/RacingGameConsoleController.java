package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import racingcar.domain.RacingGame;
import racingcar.service.RacingGameService;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.Input;
import racingcar.view.Output;

@Controller
public class RacingGameConsoleController {

    private final RacingGameService racingGameService;

    @Autowired
    public RacingGameConsoleController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void run() {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        while (true) {
            Output.printMessage("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
            String names = Input.getInput();
            Output.printMessage("시도할 회수는 몇회인가요?");
            int tryCount = Input.getTryCount(Input.getInput());
            RacingGame racingGame = racingGameService.playAndSave(names, tryCount, numberGenerator);
            printResult(racingGame);
        }
    }

    private void printResult(final RacingGame racingGame) {
        Output.printCarsResult(racingGame.getCars());
        Output.printWinner(racingGame.getWinner());
    }
}
