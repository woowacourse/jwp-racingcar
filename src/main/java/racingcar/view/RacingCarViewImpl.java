package racingcar.view;

import java.util.List;
import racingcar.dto.RacingCarNamesDto;
import racingcar.dto.RacingCarStatusDto;
import racingcar.dto.RacingCarWinnerDto;
import racingcar.dto.TryCountDto;

public class RacingCarViewImpl implements RacingCarView {

    @Override
    public RacingCarNamesDto receiveCarNames() {
        return RacingCarNamesDto.from(InputView.readCarNames());
    }

    @Override
    public TryCountDto receiveTryCount() {
        return TryCountDto.of(InputView.readTryCount());
    }

    @Override
    public void printRacingProgress(List<RacingCarStatusDto> responses) {
        for (RacingCarStatusDto response : responses) {
            OutputView.printRacingProgress(response);
        }
        System.out.println();
    }

    @Override
    public void printWinners(RacingCarWinnerDto response) {
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
