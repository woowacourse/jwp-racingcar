package racingcar.domain;

public class Car {

    private static final int THRESHOLD = 4;
    private static final int DEFAULT_INITIAL_POSITION = 0;

    private final CarName name;
    private int position;

    public Car(String name) {
        this(name, DEFAULT_INITIAL_POSITION);
    }

    public Car(String name, int position) {
        this.name = new CarName(name);
        this.position = position;
    }

    public void move(int power) {
        if (power >= THRESHOLD) {
            this.position++;
        }
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name.getName();
    }

    public boolean isSameDistance(int position) {
        return this.position == position;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Car
                && ((Car) object).getName()
                .equals(this.name.getName());
    }

    @Override
    public int hashCode() {
        return this.name.getName()
                .hashCode();
    }

}