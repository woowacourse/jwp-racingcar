package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Car {
    private static final int MOVE_LOWER_BOUND = 4;

    private final String name;
    private int distance;
    private List<Integer> distanceRecord;

    public Car(String name) {
        this.name = name;
        this.distance = 0;
        distanceRecord = new ArrayList<>();
    }

    public Car(String name, int distance) {
        this.name = name;
        this.distance = distance;
        distanceRecord = new ArrayList<>();
    }

    public void move(int power) {
        if (power >= MOVE_LOWER_BOUND) {
            this.distance++;
        }
        distanceRecord.add(this.distance);
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

    public int getDistanceRecord(int index) {
        return distanceRecord.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return distance == car.distance && Objects.equals(name, car.name) && Objects.equals(distanceRecord, car.distanceRecord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance, distanceRecord);
    }
}
