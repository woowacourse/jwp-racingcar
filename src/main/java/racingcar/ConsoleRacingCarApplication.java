package racingcar;

import racingcar.controller.RacingController;
import racingcar.dto.RacingResultDto;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;
import racingcar.view.FindingCommand;
import racingcar.view.ReplayCommand;

import java.util.List;
public class ConsoleRacingCarApplication {
    private final RacingController controller;

    public ConsoleRacingCarApplication(RacingController controller) {
        this.controller = controller;
    }

    public void run() {
        ReplayCommand replayCommand = ReplayCommand.YES;
        while (replayCommand == ReplayCommand.YES) {
            playRace(controller);
            replayCommand = ConsoleInputView.getReplayCommand();
        }

        findAllResult(controller);
    }

    private void playRace(RacingController controller) {
        RacingResultDto racingResult = controller.playRacing(ConsoleInputView.getRacingInfoRequest());
        ConsoleOutputView.printResult(racingResult);
    }

    private void findAllResult(RacingController controller) {
        if (ConsoleInputView.getFindingCommand() == FindingCommand.YES) {
            List<RacingResultDto> results = controller.findAllRaceResults();
            ConsoleOutputView.printResults(results);
        }
    }
}
