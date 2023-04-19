package racingcar.domain;

import racingcar.exception.WrongRoundException;

public class Round {

    private static final int MIN_VALUE = 1;

    private final int round;

    private Round(final int round) {
        this.round = round;
    }

    public static Round from(final String inputRound) {
        int round = mapToRound(inputRound);
        validateRound(round);

        return new Round(round);
    }

    private static int mapToRound(String inputRound) {
        try {
            return Integer.parseInt(inputRound);
        } catch (NumberFormatException exception) {
            throw new WrongRoundException();
        }
    }

    private static void validateRound(int round) {
        if (round < MIN_VALUE) {
            throw new WrongRoundException();
        }
    }

    public int getRound() {
        return round;
    }
}
