package racingcar.dto;

import racingcar.domain.Car;
import racingcar.entity.PlayerRow;

public class CarStatusResponseDto {

	private final String name;
	private final int position;

	private CarStatusResponseDto (final String name, final int position) {
		this.name = name;
		this.position = position;
	}

	public static CarStatusResponseDto toDto (final Car car) {
		return new CarStatusResponseDto(car.getCarName(), car.getDistance());
	}

	public static CarStatusResponseDto toDto (final PlayerRow playerRow) {
		return new CarStatusResponseDto(playerRow.getName(), playerRow.getPosition());
	}

	public String getName () {
		return name;
	}

	public int getPosition () {
		return position;
	}
}
