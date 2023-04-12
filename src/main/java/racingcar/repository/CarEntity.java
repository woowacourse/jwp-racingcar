package racingcar.repository;

import racingcar.domain.Car;

public class CarEntity {

    private final String name;
    private final int position;
    private Integer id;

    public CarEntity(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public CarEntity(final Car car, final int id) {
        this(car.getCarName(), car.getPosition());
        this.id = id;
    }

    public CarEntity(final int id, final String name, final int position) {
        this(name, position);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Integer getId() {
        return id;
    }

    public Car toDomain() {
        return new Car(name, position);
    }
}
