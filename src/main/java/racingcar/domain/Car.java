package racingcar.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
public class Car implements Comparable<Car> {

    private static final int MOVABLE_POWER_MIN = 4;
    private static final int NAME_LENGTH_MAX = 5;

    private final Long playRecordId;
    private final String name;
    private int position;

    // TODO 생성자 오버라이드 정적 팩토리메서드로 변경
    // TODO 막상 Dao Entity 만들고 나니, Domain Entity도 id를 가져야하나? 의문이 든다.
    public Car(Long playRecordId, String name) {
        validateName(name);
        this.playRecordId = playRecordId;
        this.name = name;
        this.position = 0;
    }

    public Car(String name) {
        validateName(name);
        this.playRecordId = null;
        this.name = name;
        this.position = 0;
    }

    public Car(String name, int position) {
        validateName(name);
        this.playRecordId = null;
        this.name = name;
        this.position = position;
    }

    public Car(Long playRecordId, String name, int position) {
        validateName(name);
        this.playRecordId = playRecordId;
        this.name = name;
        this.position = position;
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("자동차 이름이 null입니다.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 빈 문자열일 수 없습니다.");
        }
        if (name.length() > NAME_LENGTH_MAX) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
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
