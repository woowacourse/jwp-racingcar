package racingcar.domain;

public class RacingCar implements Comparable<RacingCar> {
    public static final int MOVABLE_MIN_THRESHOLD = 4;
    public static final int MOVABLE_MAX_THRESHOLD = 9;
    private static final int DEFAULT_POSITION = 1;
    private static final int DEFAULT_MOVING_AMOUNT = 1;

    private final String name;
    private int position;

    public RacingCar(String name) {
        validate(name);
        this.name = name;
        this.position = DEFAULT_POSITION;
    }

    private void validate(final String name) {
        if (2 > name.length() || name.length() > 5) {
            throw new IllegalArgumentException("이름의 길이는 2에서 5입니다.");
        }
    }

    public void advance(int number) {
        if (MOVABLE_MIN_THRESHOLD <= number && number <= MOVABLE_MAX_THRESHOLD) {
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
