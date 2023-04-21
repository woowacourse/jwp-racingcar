package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.dao.ConsoleGameDao;
import racingcar.dao.ConsolePlayerDao;
import racingcar.domain.RandomNumberGenerator;
import racingcar.service.RacingGameService;
import racingcar.view.InputParser;
import racingcar.view.InputValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class RacingGameApplication {
    public static void main(String[] args) {
        final RacingGameService racingGameService = generateRacingGameService();
        final RacingGameController racingGameController = new RacingGameController(
                racingGameService,
                new InputView(new InputValidator(), new InputParser(), new Scanner(System.in)),
                new OutputView()
        );
        racingGameController.run();
    }

    private static RacingGameService generateRacingGameService() {
        return new RacingGameService(
                new RandomNumberGenerator(),
                new ConsoleGameDao(), new ConsolePlayerDao()
        );
    }
}
