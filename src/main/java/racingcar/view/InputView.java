package racingcar.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {

    private static final InputView INSTANCE = new InputView();

    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final String CAR_NAMES_DELIMITER = ",";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String CAR_NAMES_BLANK_ERROR = ERROR_PREFIX + "경주할 자동차 이름이 입력되지 않았습니다.";
    private static final String CAR_NAMES_DUPLICATE_ERROR = ERROR_PREFIX + "경주할 자동차 이름이 중복되었습니다.";
    private static final String CAR_NAME_LENGTH_ERROR = ERROR_PREFIX + "경주할 자동차 이름이 5글자 초과했습니다.";
    private static final String CAR_NAME_BLANK_ERROR = ERROR_PREFIX + "각 자동차 이름은 빈 값일 수 없습니다.";
    private static final String TRY_NUM_BLANK_ERROR = ERROR_PREFIX + "시도할 횟수가 입력되지 않았습니다.";
    private static final String TRY_NUM_NOT_INTEGER_ERROR = ERROR_PREFIX + "시도할 횟수가 정수가 아닙니다.";
    private static final String TRY_NUM_NOT_POSITIVE_ERROR = ERROR_PREFIX + "시도할 횟수는 1 이상이어야 합니다.";

    private InputView() {
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public List<String> readCarNames() {
        final Scanner scanner = new Scanner(System.in);
        final String carNames = scanner.nextLine();
        validateCarNames(carNames);
        final String[] splitCarNames = getSplitCarNames(carNames);
        validateSplitCarNames(splitCarNames);

        return Arrays.asList(splitCarNames);
    }

    private void validateCarNames(final String carNames) {
        if (isBlank(carNames)) {
            throw new IllegalArgumentException(CAR_NAMES_BLANK_ERROR);
        }
    }

    private String[] getSplitCarNames(final String carNames) {
        return carNames.split(CAR_NAMES_DELIMITER, -1);
    }

    private void validateSplitCarNames(final String[] splitCarNames) {
        for (final String carName : splitCarNames) {
            validateCarName(carName);
        }
        if (isDuplicated(splitCarNames)) {
            throw new IllegalArgumentException(CAR_NAMES_DUPLICATE_ERROR);
        }
    }

    private boolean isBlank(final String inputValue) {
        return inputValue.length() < 1;
    }

    private void validateCarName(final String carName) {
        if (isCorrectCarNameLength(carName)) {
            throw new IllegalArgumentException(CAR_NAME_LENGTH_ERROR);
        }
        if (isBlank(carName)) {
            throw new IllegalArgumentException(CAR_NAME_BLANK_ERROR);
        }
    }

    private boolean isDuplicated(final String[] splitCarNames) {
        final Set<String> removedDuplicatedSplitCarNames = Arrays.stream(splitCarNames)
                .collect(Collectors.toSet());
        return splitCarNames.length != removedDuplicatedSplitCarNames.size();
    }

    private boolean isCorrectCarNameLength(final String carName) {
        return carName.length() > MAX_CAR_NAME_LENGTH;
    }

    public int readTryNum() {
        final Scanner scanner = new Scanner(System.in);
        final String tryNum = scanner.nextLine();
        validateTryNum(tryNum);
        return Integer.parseInt(tryNum);
    }

    private void validateTryNum(final String tryNum) {
        if (isBlank(tryNum)) {
            throw new IllegalArgumentException(TRY_NUM_BLANK_ERROR);
        }
        if (isNotInteger(tryNum)) {
            throw new IllegalArgumentException(TRY_NUM_NOT_INTEGER_ERROR);
        }
        if (isNotPositive(tryNum)) {
            throw new IllegalArgumentException(TRY_NUM_NOT_POSITIVE_ERROR);
        }
    }

    private boolean isNotInteger(final String tryNum) {
        try {
            Integer.parseInt(tryNum);
        } catch (final NumberFormatException error) {
            return true;
        }
        return false;
    }

    private boolean isNotPositive(final String tryNum) {
        return Integer.parseInt(tryNum) < 1;
    }


}
