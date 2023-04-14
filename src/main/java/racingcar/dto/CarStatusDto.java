package racingcar.dto;

import racingcar.domain.CarName;
import racingcar.domain.CarPosition;

public class CarStatusDto {

    private final String name;
    private final int position;

    public CarStatusDto(final CarName name, final CarPosition position) {
        this.name = name.getName();
        this.position = position.getPosition();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
