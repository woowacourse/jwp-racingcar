package racingcar.dto;

import racingcar.domain.CarName;
import racingcar.domain.Position;

public class CarStatusDto {
    private final String name;
    private final int currentPosition;

    public CarStatusDto(final CarName carName, final Position currentPosition) {
        this.name = carName.getName();
        this.currentPosition = currentPosition.getPosition();
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
