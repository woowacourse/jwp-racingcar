package racingcar.dto;

import racingcar.domain.Car;
import racingcar.entity.Player;

public class RacingCarStatusDto {
    private final String name;
    private final int position;

    private RacingCarStatusDto(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static RacingCarStatusDto from(Car car) {
        return new RacingCarStatusDto(car.getName(), car.getMovedLength());
    }

    public static RacingCarStatusDto from(Player player) {
        return new RacingCarStatusDto(player.getName(), player.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("%s : %s", name, "-".repeat(position));
    }
}
