package racingcar.entity;

public class CarEntity {

    private final String name;
    private final int position;

    private CarEntity(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static CarEntity of(final String name, final int position) {
        return new CarEntity(name, position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
