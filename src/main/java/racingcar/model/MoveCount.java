package racingcar.model;

import java.util.regex.Pattern;

public class MoveCount {

    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]*$");
    private static final String NOT_NUMERIC = "자연수만 입력 가능합니다.";
    private static final String INVALID_MOVE_COUNT_RANGE = "1회 이상 이동해야 합니다.";
    private static final String OUT_OF_INT_RANGE = "int 범위를 초과하는 입력은 불가능합니다.";

    private final int moveCount;

    public MoveCount(int moveCount) {
        validateRange(moveCount);
        this.moveCount = moveCount;
    }

    public static MoveCount from(String moveCount) {
        validateNumeric(moveCount);
        validateIntRange(moveCount);
        return new MoveCount(Integer.parseInt(moveCount));
    }

    private static void validateNumeric(String input) {
        if (!NUMBER_REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException(NOT_NUMERIC);
        }
    }

    private static void validateIntRange(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(OUT_OF_INT_RANGE);
        }
    }

    private void validateRange(int moveCount) {
        if (moveCount <= 0) {
            throw new IllegalArgumentException(INVALID_MOVE_COUNT_RANGE);
        }
    }

    public int getMoveCount() {
        return moveCount;
    }
}
