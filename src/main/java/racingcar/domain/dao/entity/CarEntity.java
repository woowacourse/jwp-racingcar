package racingcar.domain.dao.entity;

public class CarEntity {

    private final Long carId;
    private final String name;
    private final int position;

    public CarEntity(final Long carId, final String name, final int position) {
        this.carId = carId;
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
