package racingcar.domain;

public class RacingCar {
    private static final int THRESHOLD = 4;
    private final Name name;
    private final ThrustGenerator thrustGenerator;
    private int position;

    public RacingCar(final Name name) {
        this.name = name;
        this.thrustGenerator = new RandomThrustGenerator();
    }

    public RacingCar(final Name name, final ThrustGenerator thrustGenerator) {
        this.name = name;
        this.thrustGenerator = thrustGenerator;
    }

    public void move() {
        if (isMovable(thrustGenerator.generate())) {
            position++;
        }
    }

    private boolean isMovable(final int thrust) {
        return thrust >= THRESHOLD;
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position;
    }

}


