package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.vo.Name;
import racingcar.domain.vo.Round;
import racingcar.strategy.RacingNumberGenerator;

public class RacingGame {

	private final Cars cars;
	private final Round round;

	private RacingGame(final Cars cars, final Round round) {
		this.cars = cars;
		this.round = round;
	}

	public static RacingGame of(final List<String> carNames, final int tryCount) {
		final Cars cars = Cars.from(carNames);
		final Round round = new Round(tryCount);

		return new RacingGame(cars, round);
	}

	public void playGame(final RacingNumberGenerator generator) {
		final int round = this.round.getValue();
		for (int count = 0; count < round; count++) {
			cars.race(generator);
		}
	}

	public String findWinnerNames() {
		return cars.findWinnerNames().stream()
			.map(Name::getValue)
			.collect(Collectors.joining(","));
	}

	public List<Car> getCars() {
		return cars.getCars();
	}

	public Round getRound() {
		return round;
	}
}
