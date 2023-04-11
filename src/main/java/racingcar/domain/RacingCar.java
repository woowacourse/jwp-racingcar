package racingcar.domain;

public class RacingCar implements Comparable<RacingCar> {
    private static final Integer DEFAULT_POSITION = 0;
    private static final Integer DEFAULT_MOVING_AMOUNT = 1;
    private final String name;
    private Integer position;

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

    public Integer getPosition() {
        return position;
    }

    @Override
    public int compareTo(RacingCar o) {
        return o.position - this.position;
    }
}
