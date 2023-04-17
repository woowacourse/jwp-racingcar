package racingcar.dao.entity;

import racingcar.domain.Car;

public class CarEntity {

    private final String name;
    private final int position;
    private Integer id;

    public CarEntity(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public CarEntity(final int id, final String name, final int position) {
        this(name, position);
        this.id = id;
    }

    public static CarEntity fromDomain(final Car car) {
        return new CarEntity(car.getCarName(), car.getPosition());
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
}
