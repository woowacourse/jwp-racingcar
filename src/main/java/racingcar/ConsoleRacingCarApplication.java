package racingcar;

import racingcar.controller.RacingController;
import racingcar.dao.CarInfoCollectionRepository;
import racingcar.dao.RacingCollectionRepository;
import racingcar.dto.RacingResultResponseDto;
import racingcar.service.RacingService;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;
import racingcar.view.FindingCommand;
import racingcar.view.ReplayCommand;

import java.util.List;

public class ConsoleRacingCarApplication {
    public static void run() {
        RacingController controller = new RacingController(
                new RacingService(new RandomNumberGenerator(),
                        new RacingCollectionRepository(),
                        new CarInfoCollectionRepository())
        );

        ReplayCommand replayCommand = ReplayCommand.YES;
        while (replayCommand == ReplayCommand.YES) {
            playRace(controller);
            replayCommand = ConsoleInputView.getReplayCommand();
        }

        findAllResult(controller);
    }

    private static void playRace(RacingController controller) {
        RacingResultResponseDto racingResult = controller.playRacing(ConsoleInputView.getRacingInfoRequest());
        ConsoleOutputView.printResult(racingResult);
    }

    private static void findAllResult(RacingController controller) {
        if (ConsoleInputView.getFindingCommand() == FindingCommand.YES) {
            List<RacingResultResponseDto> results = controller.findAllRaceResults();
            ConsoleOutputView.printResults(results);
        }
    }
}
