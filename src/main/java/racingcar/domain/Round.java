package racingcar.domain;

import racingcar.exception.BadRequestException;

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
            throw new BadRequestException("올바른(1 이상의 숫자) 라운드를 입력해주세요.");
        }
    }

    private static void validateRound(int round) {
        if (round < MIN_VALUE) {
            throw new BadRequestException("올바른(1 이상의 숫자) 라운드를 입력해주세요.");
        }
    }

    public int getRound() {
        return round;
    }
}
