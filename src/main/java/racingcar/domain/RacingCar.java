package racingcar.domain;

public class RacingCar implements Comparable<RacingCar> {
    public static final int MOVABLE_MIN_THRESHOLD = 4;
    public static final int MOVABLE_MAX_THRESHOLD = 9;
    private static final int DEFAULT_POSITION = 1;
    private static final int DEFAULT_MOVING_AMOUNT = 1;

    private final String name;
    private int position;

    public RacingCar(String name) {
        this.name = name;
        this.position = DEFAULT_POSITION;
    }

    public void advance(int number) {
        if (number >= MOVABLE_MIN_THRESHOLD && number <= MOVABLE_MAX_THRESHOLD) {
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
