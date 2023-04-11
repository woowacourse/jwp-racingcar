package racingcar.domain;

public class Car {

    private final Name name;
    private int position = 0;

    public Car(final String name, final int order) {
        this.name = new Name(name, order);
    }

    public void drive(int distance) {
        position += distance;
    }

    public boolean isWinner(int maxDistance) {
        return position == maxDistance;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name.getName();
    }

    public int getIdentifier() {
        return name.getIdentifier();
    }
}
