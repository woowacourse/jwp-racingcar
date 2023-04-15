package racingcar.view;

import java.util.List;
import racingcar.dto.RacingCarNamesRequest;
import racingcar.dto.RacingCarResultDto;
import racingcar.dto.WinnerDto;
import racingcar.dto.TryCountRequest;

public interface RacingCarView {
    RacingCarNamesRequest receiveCarNames();

    TryCountRequest receiveTryCount();

    void printRacingProgress(List<RacingCarResultDto> responses);

    void printWinners(WinnerDto response);

    void printStartMessage();

    void printExceptionMessage(RuntimeException e);
}
