package racingcar;

import java.util.Scanner;
import racingcar.controller.ConsoleRacingGameController;
import racingcar.dao.ConsoleCarDao;
import racingcar.dao.ConsoleGameDao;
import racingcar.domain.RandomNumberGenerator;
import racingcar.service.RacingGameMapper;
import racingcar.service.RacingGameService;
import racingcar.view.InputParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingGameApplication {
    public static void main(String[] args) {
        final RacingGameService racingGameService = generateRacingGameService();
        final ConsoleRacingGameController racingGameController = new ConsoleRacingGameController(
                racingGameService,
                new InputView(new InputParser(), new Scanner(System.in)),
                new OutputView()
        );
        racingGameController.run();
    }

    private static RacingGameService generateRacingGameService() {
        return new RacingGameService(
                new RandomNumberGenerator(),
                new RacingGameMapper(),
                new ConsoleGameDao(),
                new ConsoleCarDao()
        );
    }
}
