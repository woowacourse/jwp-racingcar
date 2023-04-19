package racingcar.entity;

public class CarEntity {

    private final String name;
    private final int position;

    public CarEntity(final String name, int position) {
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
