package racingcar.dto;

import racingcar.domain.Car;
import racingcar.entity.Player;

public class CarResponse {

    private final String name;
    private final int position;

    private CarResponse(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static CarResponse from(Car car) {
        String name = car.getName();
        int position = car.getDrivenDistance();
        return new CarResponse(name, position);
    }

    public static CarResponse from(Player player) {
        return new CarResponse(player.getName(), player.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
