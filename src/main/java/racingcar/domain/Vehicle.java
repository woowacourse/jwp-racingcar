package racingcar.domain;

import java.util.Objects;

public class Vehicle implements MovableStrategy {
    private final String name;
    private int distance;

    public Vehicle(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public void updateDistance() {
        distance++;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return distance == vehicle.distance && Objects.equals(name, vehicle.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance);
    }
}
