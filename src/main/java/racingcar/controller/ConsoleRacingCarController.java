package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.ResultDTO;
import racingcar.response.RacingGameResponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleRacingCarController(final InputView inputView, final OutputView outputView, final RacingCarService racingCarService) {
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
