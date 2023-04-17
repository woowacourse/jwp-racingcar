package racing.domain;

public class Car {

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

    public void move() {
        this.step++;
    }

    public int getCarStep(int winnerStep) {
        return Math.max(winnerStep, step);
    }

    public String getName() {
        return carName.getValue();
    }

    public int getStep() {
        return step;
    }

}
