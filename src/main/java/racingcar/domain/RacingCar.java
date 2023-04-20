package racingcar.domain;

import racingcar.dao.Player;

public class RacingCar {

    private static final int MOVE_THRESHOLD = 4;

    private final Name name;
    private int position;
    private Pickable picker;

    private RacingCar(final Name name) {
        this(name.getName(), new RandomPicker());
    }

    public RacingCar(final String carName, final Pickable picker) {
        this(carName, 0, picker);
    }

    private RacingCar(final String carName, final int position, final Pickable picker) {
        this.name = new Name(carName);
        this.position = position;
        this.picker = picker;
    }


    public static RacingCar createRandomMoveRacingCar(final Name name) {
        return new RacingCar(name);
    }

    public static RacingCar from(final Player player) {
        return new RacingCar(player.getName(), player.getPosition(), new RandomPicker());
    }

    public void move() {
        if (isMovable()) {
            position++;
        }
    }

    private boolean isMovable() {
        return picker.pickNumber() >= MOVE_THRESHOLD;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name.getName();
    }
}
