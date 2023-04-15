package racingcar.view;

import java.util.List;
import racingcar.dto.RacingCarNamesRequest;
import racingcar.dto.RacingCarResultDto;
import racingcar.dto.RacingCarWinnerResponse;
import racingcar.dto.TryCountRequest;

public interface RacingCarView {
    RacingCarNamesRequest receiveCarNames();

    TryCountRequest receiveTryCount();

    void printRacingProgress(List<RacingCarResultDto> responses);

    void printWinners(RacingCarWinnerResponse response);

    void printStartMessage();

    void printExceptionMessage(RuntimeException e);
}
