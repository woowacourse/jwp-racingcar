package racingcar.dto;

import java.util.List;

public class ResultDto {
	private String winners;
	private List<CarDto> racingCars;

	public String getWinners() {
		return winners;
	}

	public void setWinners(String winners) {
		this.winners = winners;
	}

	public List<CarDto> getRacingCars() {
		return racingCars;
	}

	public void setRacingCars(List<CarDto> racingCars) {
		this.racingCars = racingCars;
	}
}
