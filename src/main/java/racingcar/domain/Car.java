package racingcar.domain;

public class Car {
    public static final int MINIMUM_NUMBER_TO_MOVE = 4;
    private final CarName name;
    private final Position position;

    public Car(final CarName name, final Position position) {
        this.name = name;
        this.position = position;
    }

    public static Car of(String name, int position) {
        return new Car(new CarName(name), new Position(position));
    }

    public void move(NumberGenerator numberGenerator) {
        int randomNumber = numberGenerator.generate();

        if (isMovable(randomNumber)) {
            position.move();
        }
    }

    public int compareTo(Car other) {
        return this.position.getPosition() - other.position.getPosition();
    }

    public boolean isSamePosition(Car target) {
        return position.equals(target.position);
    }

    private boolean isMovable(int number) {
        return number >= MINIMUM_NUMBER_TO_MOVE;
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }

}
