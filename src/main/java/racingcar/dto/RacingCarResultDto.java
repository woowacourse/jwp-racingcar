package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.Position;

public class RacingCarResultDto {
    private final String name;
    private final int position;

    public RacingCarResultDto(Name name, Position position) {
        this.name = name.getName();
        this.position = position.getValue();
    }

    public static RacingCarResultDto of(Car car) {
        return new RacingCarResultDto(car.getName(), car.getMovedLength());
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
