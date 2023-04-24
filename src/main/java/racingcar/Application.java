package racingcar;

import racingcar.controller.RacingController;
import racingcar.controller.TrackRequest;
import racingcar.controller.TrackResponse;
import racingcar.dao.RacingConsoleDao;
import racingcar.exception.CustomException;
import racingcar.service.RacingService;
import racingcar.view.inputview.InputView;
import racingcar.view.outputview.OutputView;

public class Application {

    public static void main(String[] args) {
        RacingService racingService = new RacingService(new RacingConsoleDao());
        RacingController controller = new RacingController(racingService);

        TrackResponse response = play(controller);
        printResult(response);
    }

    private static TrackResponse play(RacingController controller) {
        try {
            TrackRequest request = makeRequest();
            return controller.play(request).getBody();
        } catch (CustomException customException) {
            OutputView.printErrorMessage(customException);
            return play(controller);
        }
    }

    private static TrackRequest makeRequest() {
        String carNames = InputView.inputCarNames();
        String trialTimes = InputView.inputTrialTimes();
        return new TrackRequest(carNames, trialTimes);
    }

    private static void printResult(TrackResponse response) {
        OutputView.printWinnerCars(response);
        OutputView.printCurrentCarsPosition(response);
    }
}
