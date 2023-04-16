package racingcar.domain.Car;

public class Car {

    private static final int MINIMUM_NUMBER_TO_MOVE = 4;

    private final Name name;
    private final Position position;

    public Car(String name, int startPoint) {
        this.name = new Name(name);
        this.position = new Position(startPoint);
    }

    public void move(NumberGenerator numberGenerator) {
        int randomNumber = numberGenerator.generate();
        if (isMovable(randomNumber)) {
            position.move();
        }
    }

    private boolean isMovable(int number) {
        return number >= MINIMUM_NUMBER_TO_MOVE;
    }

    public int compareTo(Car other) {
        return this.position.getPosition() - other.position.getPosition();
    }

    public boolean isSamePosition(Car target) {
        return position.equals(target.position);
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }
}
