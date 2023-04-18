package racingcar.domain.cars;

import java.util.Objects;

public class RacingCar {
    private static final int LEAST_CONDITION = 4;
    private static final int MAX_NAME_LENGTH = 5;
    private static final int ONE_STEP = 1;

    private Long id;
    private final String name;
    private final Position position;


    public RacingCar(String name) {
        validate(name);
        this.name = name;
        this.position = new Position();
    }

    public RacingCar(String name, int position) {
        this.name = name;
        this.position = new Position(position);
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 공백일 수 없습니다.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차의 이름은 다섯 글자 이하여야합니다.");
        }
    }

    public void moveDependingOn(int pickedNumber) {
        if (pickedNumber >= LEAST_CONDITION) {
            position.add(ONE_STEP);
        }
    }

    @Override
    public boolean equals(Object o) {
        RacingCar racingCar = (RacingCar) o;
        if (id == null || racingCar.id == null) {
            return this == o;
        }
        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        return id.equals(racingCar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position.getValue();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
