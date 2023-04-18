package racing.domain;

public class Car {

    private static final int FUEL_THRESHOLDS = 4;
    private final Name name;
    private int position = 0;
    private boolean isWinner = false;

    public Car(final String name) {
        this.name = new Name(name);
    }

    public void drive(final int fuel) {
        if (fuel >= FUEL_THRESHOLDS) {
            position += 1;
        }
    }

    public void updateWinningCondition(int maxDistance) {
        if (position == maxDistance) {
            isWinner = true;
            return;
        }
        isWinner = false;
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
