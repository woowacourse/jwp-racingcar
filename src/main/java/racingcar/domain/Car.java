package racingcar.domain;

import java.util.Objects;

public class Car implements Comparable<Car> {

    private static final int MOVABLE_POWER_MIN = 4;
    private static final int NAME_LENGTH_MAX = 5;

    private final Long playId;
    private final String name;
    private int position;

    // TODO 생성자 오버라이드 정적 팩토리메서드로 변경
    public Car(String name) {
        validateName(name);
        this.playId = null;
        this.name = name;
        this.position = 0;
    }

    public Car(String name, int position) {
        validateName(name);
        this.playId = null;
        this.name = name;
        this.position = position;
    }

    public Car(long playId, String name, int position) {
        validateName(name);
        this.playId = playId;
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

    public Long getPlayId() {
        return playId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(Car other) {
        return this.position - other.position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return position == car.position && Objects.equals(playId, car.playId) && Objects.equals(name,
                car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playId, name, position);
    }
}
