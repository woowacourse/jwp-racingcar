package racingcar.view.outputview;

import java.util.List;
import java.util.StringJoiner;
import racingcar.controller.CarResponse;
import racingcar.exception.DuplicateCarNamesException;
import racingcar.exception.ExceedCarNameLengthException;
import racingcar.exception.HasBlankCarNameException;
import racingcar.exception.InvalidCarNameFormatException;
import racingcar.exception.InvalidRangeTrialTimesException;
import racingcar.exception.InvalidTrialTimesFormatException;
import racingcar.exception.NotExistCarsException;

public class KoreanOutputView extends OutputView {
    public static final String POSITION_CAR_FORMAT_SYMBOL = "-";
    public static final String POSITION_CAR_STATE_FORMAT = "%s : %s";
    private static final String WINNER_MESSAGE = "%s가 최종 우승했습니다.";
    private static final String LINE_BREAK = "\n";

    private static final String NOT_EXIST_CARS_ERROR_MESSAGE = "[ERROR] 게임에 참여한 자동차가 없습니다.";
    private static final String DUPLICATE_CAR_NAMES_ERROR_MESSAGE = "[ERROR] 중복된 차 이름이 존재합니다.";
    private static final String EXCEED_CAR_NAME_LENGTH_ERROR_MESSAGE = "[ERROR] 자동차 이름은 다섯 글자 이하여야 합니다.";
    private static final String HAS_BLANK_CAR_NAME_ERROR_MESSAGE = "[ERROR] 비어있는 자동차 이름이 존재합니다.";
    private static final String INVALID_CAR_NAME_FORMAT_ERROR_MESSAGE = "[ERROR] 자동차 이름은 문자와 숫자만 가능합니다.";
    private static final String INVALID_RANGE_TRIAL_TIMES_ERROR_MESSAGE = "[ERROR] 시도 횟수는 1 이상 100 이하여야 합니다.";
    private static final String INVALID_TRIAL_TIMES_FORMAT_ERROR_MESSAGE = "[ERROR] 시도 횟수는 숫자만 입력 가능합니다.";

    @Override
    void initialCarsErrorMessage() {
        super.insertErrorMessage(
                new NotExistCarsException(NOT_EXIST_CARS_ERROR_MESSAGE).getErrorNumber(),
                NOT_EXIST_CARS_ERROR_MESSAGE);
        super.insertErrorMessage(
                new DuplicateCarNamesException(DUPLICATE_CAR_NAMES_ERROR_MESSAGE).getErrorNumber(),
                DUPLICATE_CAR_NAMES_ERROR_MESSAGE);
    }

    @Override
    void initialCarErrorMessage() {
        super.insertErrorMessage(
                new ExceedCarNameLengthException(EXCEED_CAR_NAME_LENGTH_ERROR_MESSAGE).getErrorNumber(),
                EXCEED_CAR_NAME_LENGTH_ERROR_MESSAGE);
        super.insertErrorMessage(
                new HasBlankCarNameException(HAS_BLANK_CAR_NAME_ERROR_MESSAGE).getErrorNumber(),
                HAS_BLANK_CAR_NAME_ERROR_MESSAGE);
        super.insertErrorMessage(
                new InvalidCarNameFormatException(INVALID_CAR_NAME_FORMAT_ERROR_MESSAGE).getErrorNumber(),
                INVALID_CAR_NAME_FORMAT_ERROR_MESSAGE);
    }

    @Override
    void initialTrackErrorMessage() {
        super.insertErrorMessage(
                new InvalidRangeTrialTimesException(INVALID_RANGE_TRIAL_TIMES_ERROR_MESSAGE).getErrorNumber(),
                INVALID_RANGE_TRIAL_TIMES_ERROR_MESSAGE);
        super.insertErrorMessage(
                new InvalidTrialTimesFormatException(INVALID_TRIAL_TIMES_FORMAT_ERROR_MESSAGE).getErrorNumber(),
                INVALID_TRIAL_TIMES_FORMAT_ERROR_MESSAGE);
    }

    @Override
    public void printCurrentCarsPosition(final List<CarResponse> carResponses) {
        StringJoiner stringJoiner = new StringJoiner(LINE_BREAK);

        carResponses.forEach(carResponse -> {
            stringJoiner.add(String.format(POSITION_CAR_STATE_FORMAT,
                    carResponse.getName(),
                    POSITION_CAR_FORMAT_SYMBOL.repeat(carResponse.getPosition())));
        });
        System.out.println(stringJoiner + LINE_BREAK);
    }

    @Override
    public void printWinnerCars(final String carNames) {
        System.out.println(String.format(WINNER_MESSAGE, carNames));
    }
}
