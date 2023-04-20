package racing.domain;

public class Car {

    private static final int MOVE_FORWARD_STANDARD = 4;

    private final CarName carName;
    private int step;

    public Car(CarName carName) {
        this.carName = carName;
        this.step = 0;
    }

    public Car(CarName carName, int step) {
        this.carName = carName;
        this.step = step;
    }

    public void moveForward() {
    }

    public void moveForwardByNumber(int number) {
        if (number >= MOVE_FORWARD_STANDARD) {
            this.step += 1;
        }
    }

    public String getName() {
        return carName.getValue();
    }

    public int getStep() {
        return step;
    }

}
