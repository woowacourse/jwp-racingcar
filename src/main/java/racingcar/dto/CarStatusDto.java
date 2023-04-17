package racingcar.dto;

import racingcar.domain.CarName;
import racingcar.domain.CarPosition;
import racingcar.domain.dao.entity.CarEntity;

public class CarStatusDto {

    private final String name;
    private final int position;

    public CarStatusDto(final CarName name, final CarPosition position) {
        this.name = name.getName();
        this.position = position.getPosition();
    }

    public CarStatusDto(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static CarStatusDto of(final CarEntity carEntity) {
        return new CarStatusDto(carEntity.getName(), carEntity.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
