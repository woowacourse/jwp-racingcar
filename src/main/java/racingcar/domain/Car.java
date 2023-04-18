package racingcar.domain;


public class Car {

    private final int MOVING_STANDARD_NUM = 4;
    private final int ADD_POINT = 1;

    private final Name name;
    private int position;

    public Car(String name) {
        this(name, 0);
    }

    public Car(String name, int position) {
        this.name = new Name(name);
        this.position = position;
    }

    public void move(final int number) {
        if (isAllowedToMove(number)) {
            this.position += ADD_POINT;
        }
    }

    private boolean isAllowedToMove(final int number) {
        return number >= MOVING_STANDARD_NUM;
    }

    public int position() {
        return position;
    }

    public String name() {
        return name.name();
    }


}
