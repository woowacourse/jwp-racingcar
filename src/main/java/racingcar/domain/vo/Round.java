package racingcar.domain.vo;

import java.util.Objects;

public class Round {

    private static final int MIN_VALUE = 1;

    private final int round;

    public Round(final int round) {
        validateRound(round);
        this.round = round;
    }

    private void validateRound(int round) {
        if (round < MIN_VALUE) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }

    public int getValue() {
        return round;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Round round1 = (Round) o;
        return round == round1.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }
}
