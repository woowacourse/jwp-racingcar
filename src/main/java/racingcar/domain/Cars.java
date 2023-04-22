package racingcar.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import racingcar.domain.vo.Name;
import racingcar.strategy.RacingNumberGenerator;

public class Cars {

	private final List<Car> cars;

	private Cars(List<Car> cars) {
		this.cars = cars;
	}

	public static Cars from(final List<String> carNames) {
		validateDuplication(carNames);
		final List<Car> cars = generateCars(carNames);

		return new Cars(cars);
	}

	public void race(final RacingNumberGenerator generator) {
		cars.forEach(car -> car.race(generator));
	}

	public List<Name> findWinnerNames() {
		final Car winner = findWinner();

		return cars.stream()
			.filter(car -> car.isSamePosition(winner))
			.map(Car::getName)
			.collect(toUnmodifiableList());
	}

	private static void validateDuplication(final List<String> carNames) {
		final int uniqueCarNameCount = new HashSet<>(carNames).size();
		if (uniqueCarNameCount != carNames.size()) {
			throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
		}
	}

	private static List<Car> generateCars(final List<String> carNames) {
		return carNames.stream()
			.map(Car::createCar)
			.collect(toUnmodifiableList());
	}

	private Car findWinner() {
		return cars.stream()
			.max(Car::compareTo)
			.orElseThrow(() -> new IllegalStateException("우승자를 찾을 수 없습니다."));
	}

	public List<Car> getCars() {
		return new ArrayList<>(cars);
	}

}
