package racingcar.view;

import java.util.List;
import racingcar.dto.RacingCarNamesDto;
import racingcar.dto.RacingCarStatusDto;
import racingcar.dto.RacingCarWinnerDto;
import racingcar.dto.TryCountDto;

public interface RacingCarView {
    RacingCarNamesDto receiveCarNames();

    TryCountDto receiveTryCount();

    void printRacingProgress(List<RacingCarStatusDto> responses);

    void printWinners(RacingCarWinnerDto response);

    void printStartMessage();

    void printExceptionMessage(RuntimeException e);
}
