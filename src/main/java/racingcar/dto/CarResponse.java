package racingcar.dto;

import racingcar.entity.PlayerEntity;

public class CarResponse {

    private final String name;
    private final int position;

    private CarResponse(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static CarResponse from(PlayerEntity playerEntity) {
        return new CarResponse(playerEntity.getName(), playerEntity.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
