package racingcar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.dto.CarDto;
import racingcar.dto.GameDto;
import racingcar.dto.GameResponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingCarConsoleController(final InputView inputView, final OutputView outputView,
                                      final RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void execute() {
        List<String> carNames = getCarNames();
        int tryTimes = getTryTimes();
        GameResponse gameResponse = racingCarService.play(GameDto.of(carNames, tryTimes));
        printResult(gameResponse);
    }

    private List<String> getCarNames() {
        outputView.printInputCarNamesNotice();
        return inputView.inputCarNames();
    }

    private int getTryTimes() {
        outputView.printInputTryTimesNotice();
        return inputView.inputTryTimes();
    }

    private void printResult(final GameResponse gameResponse) {
        Map<String, Integer> result = new HashMap<>();
        for (CarDto carDto : gameResponse.getRacingCars()) {
            result.put(carDto.getName(), carDto.getPosition());
        }
        outputView.printSingleRoundResult(result);
        outputView.printWinner(gameResponse.getWinners());
    }
}
