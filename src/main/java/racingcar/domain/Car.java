package racingcar.domain;

import java.util.Objects;

public class Car {
    private static final int MOVE_LOWER_BOUND = 4;

    private final String name;
    private int distance;

    public Car(String name) {
        this.name = name;
        this.distance = 0;
    }

    public Car(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public void move(int power) {
        if (power >= MOVE_LOWER_BOUND) {
            this.distance++;
        }
    }

    public boolean isWinner(int distance) {
        return this.distance == distance;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return distance == car.distance && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance);
    }
}
