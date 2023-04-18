package racingcar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.CarFactory;
import racingcar.domain.car.Cars;
import racingcar.domain.game.GameRecorder;
import racingcar.domain.game.GameResultOfCar;
import racingcar.domain.game.GameSystem;
import racingcar.domain.game.RandomSingleDigitGenerator;
import racingcar.dto.CarDTO;
import racingcar.dto.ResultDTO;
import racingcar.response.RacingGameResponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleController(final InputView inputView, final OutputView outputView, final RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void run() {
        List<String> carNames = inputView.readCarNames();

        final int count = inputView.readGameRound();

        final ResultDTO resultDTO = racingCarService.play(carNames, count);
        final String winnerNames = resultDTO.getWinners().stream()
                .collect(Collectors.joining(DELIMITER));
        final RacingGameResponse racingGameResponse = new RacingGameResponse(winnerNames, resultDTO.getCarDTOs());
        outputView.printResult(racingGameResponse);
    }
}
