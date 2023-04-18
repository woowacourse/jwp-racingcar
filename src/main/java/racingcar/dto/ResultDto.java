package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.Position;

public class ResultDto {
    private final String name;
    private final int position;

    public ResultDto(Name name, Position position) {
        this.name = name.getName();
        this.position = position.getValue();
    }

    public ResultDto(String name, int position) {
        this(new Name(name), new Position(position));
    }

    public static ResultDto of(Car car) {
        return new ResultDto(car.getName(), car.getMovedLength());
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
