package racingcar.model.car;

import racingcar.util.ValueEditor;

import java.util.Objects;

public class Car {

    private static final int MAX_NAME_LENGTH = 5;
    private static final String INVALID_NAME_LENGTH = "5자 이하의 이름을 입력해주세요.";

    private final String name;
    private int position;

    public Car(String name) {
        name = ValueEditor.removeSpace(name);
        validate(name);
        this.name = name;
        this.position = 0;
    }

    private void validate(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    public void move(boolean isMoveForward) {
        if (isMoveForward) {
            position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isSame(int position) {
        return this.position == position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return position == car.position && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
