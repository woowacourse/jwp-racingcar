package racingcar.domain;

public class RacingCar implements Comparable<RacingCar> {
    private static final int DEFAULT_POSITION = 1;
    private static final int DEFAULT_MOVING_AMOUNT = 1;
    private final String name;
    private int position;

    public RacingCar(String name) {
        this.name = name;
        this.position = DEFAULT_POSITION;
    }

    public void advance(Boolean judgementResult) {
        if (judgementResult) {
            position += DEFAULT_MOVING_AMOUNT;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(RacingCar o) {
        return o.position - this.position;
    }
}
