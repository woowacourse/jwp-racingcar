package racingcar.view;

import racingcar.dto.response.RacingCarResponse;
import racingcar.dto.response.RacingGameResponse;

import java.util.List;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

public class OutputView {

    private static final String RESULT_MESSAGE = "\n실행 결과";
    private static final String POSITION_MESSAGE_FORMAT = "{0} : {1}";
    private static final String POSITION_SYMBOL = "-";
    private static final String POSITION_MESSAGE_DELIMITER = "\n";
    private static final String WINNERS_MESSAGE_FORMAT = "{0}가 최종 우승했습니다.";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    public void printRacingGameResult(final RacingGameResponse racingGameResponse) {
        System.out.println(RESULT_MESSAGE);
        System.out.println(format(WINNERS_MESSAGE_FORMAT, racingGameResponse.getWinners()));
        printPosition(racingGameResponse.getRacingCars());
    }

    private void printPosition(final List<RacingCarResponse> racingCarResponses) {
        System.out.println(generatePositionMessages(racingCarResponses) + POSITION_MESSAGE_DELIMITER);
    }

    private String generatePositionMessages(final List<RacingCarResponse> racingCarResponses) {
        return racingCarResponses.stream()
                .map(this::generatePositionMessage)
                .collect(Collectors.joining(POSITION_MESSAGE_DELIMITER));
    }

    private String generatePositionMessage(final RacingCarResponse racingCarResponse) {
        return format(
                POSITION_MESSAGE_FORMAT,
                racingCarResponse.getName(),
                POSITION_SYMBOL.repeat(racingCarResponse.getPosition())
        );
    }

    public void printErrorMessage(final String message) {
        System.out.println(ERROR_MESSAGE + message);
    }
}
