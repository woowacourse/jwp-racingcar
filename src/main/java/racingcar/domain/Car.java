package racingcar.domain;

import java.util.Objects;

public class Car implements Comparable<Car> {

    private static final int NAME_MIN_LENGTH = 1;
    private static final int NAME_MAX_LENGTH = 5;
    private static final int MOVABLE_MIN_NUMBER = 4;
    private static final String NAME_LENGTH_ERROR = "[ERROR] 자동차 이름은 1자 이상 5자 이하여야 합니다.";

    private String name;
    private int position;
    private NumberGenerator numberGenerator;

    public Car(String name, NumberGenerator numberGenerator) {
        name = name.trim();
        validateName(name);
        this.name = name;
        this.position = 0;
        this.numberGenerator = numberGenerator;
    }

    public void goForward() {
        if (numberGenerator.generate() >= MOVABLE_MIN_NUMBER) {
            position++;
        }
    }

    public boolean isSamePosition(Car otherCar) {
        return this.position == otherCar.position;
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Car otherCar) {
        return otherCar.position - this.position;
    }

    private void validateName(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Car car = (Car) other;
        return position == car.position && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}

