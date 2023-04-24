package racingcar.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import racingcar.domain.exception.RacingCarIllegalArgumentException;


@Getter
@EqualsAndHashCode
public class Car implements Comparable<Car> {

    private static final int MOVABLE_POWER_MIN = 4;
    private static final int NAME_LENGTH_MAX = 5;

    private final Long playRecordId;
    private final String name;
    private int position;

    private Car(Long playRecordId, String name, int position) {
        validateName(name);
        this.playRecordId = playRecordId;
        this.name = name;
        this.position = position;
    }

    public static Car of(Long playRecordId, String name, int position) {
        return new Car(playRecordId, name, position);
    }

    public static Car ofPositionStart(Long playRecordId, String name) {
        return new Car(playRecordId, name, 0);
    }

    private void validateName(String name) {
        if (name == null) {
            throw new RacingCarIllegalArgumentException("자동차 이름이 null입니다.");
        }
        if (name.isEmpty()) {
            throw new RacingCarIllegalArgumentException("자동차 이름은 빈 문자열일 수 없습니다.");
        }
        if (name.length() > NAME_LENGTH_MAX) {
            throw new RacingCarIllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }
    }

    public boolean moveOrStayByPower(int power) {
        if (isMovable(power)) {
            ++this.position;
            return true;
        }
        return false;
    }

    private boolean isMovable(int power) {
        return power >= MOVABLE_POWER_MIN;
    }

    public boolean hasSamePosition(Car other) {
        return this.position == other.position;
    }

    @Override
    public int compareTo(Car other) {
        return this.position - other.position;
    }
}
