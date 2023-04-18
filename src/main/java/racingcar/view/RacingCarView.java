package racingcar.view;

import java.util.List;
import racingcar.dto.NamesDto;
import racingcar.dto.ResultDto;
import racingcar.dto.WinnerDto;
import racingcar.dto.TryCountDto;

public interface RacingCarView {
    NamesDto receiveCarNames();

    TryCountDto receiveTryCount();

    void printRacingProgress(List<ResultDto> responses);

    void printWinners(WinnerDto response);

    void printStartMessage();

    void printExceptionMessage(RuntimeException e);
}
