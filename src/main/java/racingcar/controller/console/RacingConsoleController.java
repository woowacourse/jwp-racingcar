package racingcar.controller.console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.controller.TrackCreateResponse;
import racingcar.service.RacingService;
import racingcar.view.inputview.InputView;
import racingcar.view.outputview.OutputView;

public class RacingConsoleController {

    private static final String NAME_SPLIT_DELIMITER = ",";

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingService racingService;

    public RacingConsoleController(final InputView inputView, final OutputView outputView,
                                   final RacingService racingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingService = racingService;
    }

    public void start() {
        final List<String> names = Arrays.stream(inputView.inputCarNames().split(NAME_SPLIT_DELIMITER))
                .collect(Collectors.toList());
        final Integer trialTimes = inputView.inputTrialTimes();
        final TrackCreateResponse trackCreateResponse = racingService.play(names, trialTimes);

        outputView.printWinnerCars(trackCreateResponse.getWinners());
        outputView.printCurrentCarsPosition(trackCreateResponse.getRacingCars());
    }
}
