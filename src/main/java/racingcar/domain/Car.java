package racingcar.domain;

import racingcar.exception.CarNameLengthException;

public class Car {

    private static final int INIT_POSITION = 0;
    private static final int MIN_MOVABLE_POWER = 4;
    private static final int MAX_CAR_NAME_LENGTH = 5;

    private final String name;
    private int position;

    public Car(String name) {
        validateCarName(name);
        this.name = name;
        position = INIT_POSITION;
    }

    public Car(String name, int position) {
        validateCarName(name);
        this.name = name;
        this.position = position;
    }

    public void move(int power) {
        if (movable(power)) {
            position = position + 1;
        }
    }

    private boolean movable(int power) {
        return power >= MIN_MOVABLE_POWER;
    }

    public void validateCarName(String name) {
        if (name.length() > MAX_CAR_NAME_LENGTH) {
            throw new CarNameLengthException();
        }
    }

    public boolean equalsPosition(Car other) {
        return this.position == other.position;
    }

    public int compareTo(Car other) {
        return this.position - other.position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

}
