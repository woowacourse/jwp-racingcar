package racingcar.wrapper;

import racingcar.exception.BadRequestException;
import racingcar.exception.ExceptionMessage;

public class Round {

    private static final int MIN_VALUE = 1;

    private final int round;

    public Round(String inputRound) {
        int round = mapToRound(inputRound);

        validateRound(round);
        this.round = round;
    }

    private int mapToRound(String inputRound) {
        try {
            return Integer.parseInt(inputRound);
        } catch (NumberFormatException exception) {
            throw new BadRequestException(ExceptionMessage.WRONG_ROUND);
        }
    }

    private void validateRound(int round) {
        if (round < MIN_VALUE) {
            throw new BadRequestException(ExceptionMessage.WRONG_ROUND);
        }
    }

    public int getRound() {
        return round;
    }
}
