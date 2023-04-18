package racingcar.controller;

import racingcar.domain.RacingGame;
import racingcar.service.RacingGameService;
import racingcar.view.Input;
import racingcar.view.Output;

public class RacingGameConsoleController {
    private final RacingGameService racingGameService = new RacingGameService();

    public void run() {
        Output.print("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String names = Input.getInput();
        Output.print("시도할 회수는 몇회인가요?");
        Integer count = Input.getTryCount(Input.getInput());

        RacingGame racingGame = racingGameService.play(names, count);

        Output.printResult(racingGame.getWinner(), racingGame.getCars());
    }
}
