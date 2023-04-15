package racingcar.view;

import java.util.List;
import racingcar.dto.RacingCarNamesRequest;
import racingcar.dto.RacingCarResultDto;
import racingcar.dto.WinnerDto;
import racingcar.dto.TryCountRequest;

public class RacingCarViewImpl implements RacingCarView {

    @Override
    public RacingCarNamesRequest receiveCarNames() {
        return RacingCarNamesRequest.of(InputView.readCarNames());
    }

    @Override
    public TryCountRequest receiveTryCount() {
        return TryCountRequest.of(InputView.readTryCount());
    }

    @Override
    public void printRacingProgress(List<RacingCarResultDto> responses) {
        for (RacingCarResultDto response : responses) {
            OutputView.printRacingProgress(response);
        }
        System.out.println();
    }

    @Override
    public void printWinners(WinnerDto response) {
        OutputView.printWinners(response);
    }

    @Override
    public void printStartMessage() {
        OutputView.printStartMessage();
    }

    @Override
    public void printExceptionMessage(RuntimeException e) {
        OutputView.printExceptionMessage(e);
    }
}
