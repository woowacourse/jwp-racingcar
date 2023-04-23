package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.util.RandomFailPowerMaker;
import racingcar.util.RandomSuccessPowerMaker;
import racingcar.utils.RandomPowerTestMaker;

class CarsTest {

	private final RandomSuccessPowerMaker randomSuccessPowerMaker = new RandomSuccessPowerMaker();
	private final RandomFailPowerMaker randomFailPowerMaker = new RandomFailPowerMaker();

	@Test
	@DisplayName("validate() : 자동차 명이 중복이면 에러가 터진다.")
	void test_validate () {
		// given
		String jay = "jay";

		// when & then
		assertThatThrownBy(() -> Cars.createByNames(List.of(jay, jay)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("자동차 이름은 중복되지 않아야합니다.");
	}

	@Test
	@DisplayName("moveAll() : 자동차가 모두 움직인다.")
	void test_moveAll_success () {
		// given
		int defaultDistance = 0;
		int expectedDistanceAfterMoveSuccess = defaultDistance + 1;

		String pobi = "pobi";
		String crong = "crong";
		Cars cars = Cars.createByNames(List.of(pobi, crong));

		// when
		cars.moveAll(randomSuccessPowerMaker);

		// then
		assertThat(cars.getCars()).allMatch(car -> car.getDistance() == expectedDistanceAfterMoveSuccess);
	}

	@Test
	@DisplayName("moveAll() : 자동차가 모두 움직이지 않는다.")
	void test_moveAll_fail () {
		// given
		int defaultDistance = 0;
		int expectedDistanceAfterMoveSuccess = defaultDistance + 1;

		String pobi = "pobi";
		String crong = "crong";
		Cars cars = Cars.createByNames(List.of(pobi, crong));

		// when
		cars.moveAll(randomFailPowerMaker);

		// then
		assertThat(cars.getCars()).allMatch(car -> car.getDistance() != expectedDistanceAfterMoveSuccess);
	}

	@Test
	@DisplayName("test_getWinnerNames() : 가장 많이 움직인 자동차를 반환해준다.")
	void test_getWinnerNames () {
		// given
		String pobi = "pobi";
		String crong = "crong";
		Cars cars = Cars.createByNames(List.of(pobi, crong));
		Queue<Integer> queue = new LinkedList<>();
		queue.add(5);
		queue.add(2);
		RandomPowerTestMaker randomPowerTestMaker = new RandomPowerTestMaker(queue);

		// when
		cars.moveAll(randomPowerTestMaker);
		List<String> winnerNames = cars.getWinnerNames();

		// then
		assertThat(winnerNames).containsOnly(pobi);
		assertThat(winnerNames.size()).isEqualTo(1);
		assertThat(winnerNames).doesNotContain(crong);
	}
}
