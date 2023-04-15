package racingcar.domain;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import racingcar.utils.NumberGenerator;

public class RacingCars {
	private final List<Car> cars;

	public RacingCars(List<Car> cars) {
		validateCarName(cars);
		this.cars = cars;
	}

	private void validateCarName(List<Car> cars) {
		long uniqueCarCount = cars.stream()
				.map(Car::getName)
				.distinct()
				.count();
		if (uniqueCarCount != cars.size()) {
			throw new IllegalArgumentException("[ERROR]: 중복된 차 이름이 있습니다.");
		}
	}

	public void moveCars(NumberGenerator numberGenerator) {
		for (Car car : cars) {
			car.move(numberGenerator.generateNumber());
		}
	}

	public List<String> getWinners() {
		int maxPosition = findMaxPosition();
		return cars.stream()
				.filter(car -> car.getPosition() == maxPosition)
				.map(Car::getName)
				.collect(toList());
	}

	private int findMaxPosition() {
		return cars.stream()
				.mapToInt(Car::getPosition)
				.max()
				.orElseThrow(() -> new IllegalStateException("[ERROR] 차가 지정되지 않았습니다."));
	}

	public List<Car> getCars() {
		return Collections.unmodifiableList(cars);
	}
}
