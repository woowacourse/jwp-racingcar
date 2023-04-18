package racingcar.domain.cars;

import java.util.Objects;

public class RacingCar {
    private static final int LEAST_CONDITION = 4;
    private static final int MAX_NAME_LENGTH = 5;
    private static final int ONE_STEP = 1;

    private final long gameId;

    private final String name;
    private final Position position;


    public RacingCar(long gameId, String name) {
        validate(name);
        this.gameId = gameId;
        this.name = name;
        this.position = new Position();
    }

    public RacingCar(long gameId, String name, int position) {
        this.gameId = gameId;
        this.name = name;
        this.position = new Position(position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position.getValue();
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 공백일 수 없습니다.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차의 이름은 다섯 글자 이하여야합니다.");
        }
    }

    public void moveDependingOn(int pickedNumber) {
        if (pickedNumber >= LEAST_CONDITION) {
            position.add(ONE_STEP);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RacingCar racingCar = (RacingCar) o;
        return gameId == racingCar.gameId && name.equals(racingCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, name);
    }
}
