package racingcar.view;

import java.util.List;
import racingcar.dto.NamesDto;
import racingcar.dto.ResultDto;
import racingcar.dto.TryCountDto;

public class RacingCarViewImpl implements RacingCarView {

    @Override
    public NamesDto receiveCarNames() {
        return NamesDto.of(InputView.readCarNames());
    }

    @Override
    public TryCountDto receiveTryCount() {
        return TryCountDto.of(InputView.readTryCount());
    }

    @Override
    public void printRacingProgress(List<ResultDto> responses) {
        for (ResultDto response : responses) {
            OutputView.printRacingProgress(response);
        }
        System.out.println();
    }

    @Override
    public void printWinners(String response) {
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
