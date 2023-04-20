package racingcar;

import racingcar.dto.RacingInfoRequestDto;
import racingcar.dto.RacingResultDto;
import racingcar.service.RacingService;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;
import racingcar.view.FindingCommand;
import racingcar.view.ReplayCommand;

import java.util.List;
import java.util.Optional;

public class ConsoleRacingCarApplication {
    private final RacingService racingService;

    public ConsoleRacingCarApplication(RacingService racingService) {
        this.racingService = racingService;
    }

    public void run() {
        ReplayCommand replayCommand = ReplayCommand.YES;
        while (replayCommand == ReplayCommand.YES) {
            playRace();
            replayCommand = ConsoleInputView.getReplayCommand();
        }

        findAllResult();
    }

    private void playRace() {
        RacingInfoRequestDto racingInfoRequest = ConsoleInputView.getRacingInfoRequest();
        int raceId = racingService.race(racingInfoRequest.getNames(), racingInfoRequest.getCount());

        Optional<RacingResultDto> dto = racingService.findRaceById(raceId);
        if (dto.isEmpty()) {
            throw new IllegalArgumentException("레이싱 정보를 식별할 수 없는 id 값입니다.");
        }
        ConsoleOutputView.printResult(dto.get());
    }

    private void findAllResult() {
        if (ConsoleInputView.getFindingCommand() == FindingCommand.YES) {
            List<RacingResultDto> results = racingService.findAllResults();
            ConsoleOutputView.printResults(results);
        }
    }
}
