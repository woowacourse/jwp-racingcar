package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingCarsTest {

	@Test
	@DisplayName("RacingCars에 중복되는 이름이 없을 경우 차가 정상적으로 추가되어야한다.")
	void carAddTest() {
		// given
		List<Car> cars = List.of(new Car("glen"), new Car("raon"));
		RacingCars racingCars = new RacingCars(cars);

		// expect
		assertThat(racingCars.getCars())
				.hasSize(2);
	}

	@Test
	@DisplayName("RacingCars에 중복되는 이름이 있을 경우 IllegalArgumentException을 발생시켜야 한다.")
	void carAddFailTest() {
		// given
		List<Car> cars = List.of(new Car("glen"), new Car("glen"));

		// expect
		assertThatThrownBy(() -> new RacingCars(cars))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
