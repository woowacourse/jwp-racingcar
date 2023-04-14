package racingcar.dto;

import java.util.ArrayList;
import java.util.List;

import racingcar.domain.Car;
import racingcar.domain.Cars;

public class DtoMapper {
	public static ResultDto toResultDto(Cars cars) {
		final ResultDto resultDto = new ResultDto();

		List<Car> carList = cars.getCars();
		List<CarDto> carDtos = DtoMapper.toCarDtos(carList);

		resultDto.setWinners(cars.winners());
		resultDto.setRacingCars(carDtos);

		return resultDto;
	}

	private static List<CarDto> toCarDtos(List<Car> carList) {
		List<CarDto> carDtos = new ArrayList<>();

		for (Car car : carList) {
			CarDto carDto = new CarDto(car.getName(), car.getPosition());
			carDtos.add(carDto);
		}

		return carDtos;
	}
}
