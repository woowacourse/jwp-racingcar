package racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import racingcar.domain.vo.Position;

class CarTest {

	@DisplayName("자동차 경주시,")
	@Nested
	class MoveTest {

		@ParameterizedTest
		@ValueSource(ints = {4, 9})
		@DisplayName("랜덤값이 4 이상이면 자동차가 전진한다.")
		void givenFourMoreNumber_thenCarMove(int randomNumber) {
			// given
			Car car = Car.createCar("BMW");
			final TestNumberGenerator testNumberGenerator = new TestNumberGenerator(List.of(randomNumber));

			// when
			car.race(testNumberGenerator);

			//then
			assertThat(car.getPosition()).isEqualTo(new Position(1));
		}

		@ParameterizedTest
		@ValueSource(ints = {0, 3})
		@DisplayName("랜덤값이 3 이하면 자동차가 정지한다.")
		void givenThreeLessNumber_thenCarStop(int randomNumber) {
			// given
			Car car = Car.createCar("BMW");
			final TestNumberGenerator testNumberGenerator = new TestNumberGenerator(List.of(randomNumber));

			// when
			car.race(testNumberGenerator);

			//then
			assertThat(car.getPosition()).isEqualTo(new Position(0));
		}

	}

	@Test
	@DisplayName("두 Car 객체 간 위치를 비교할 수 있다.")
	void positionTest() {
		//given
		final Car junpak = Car.of("준팍", 2);
		final Car moomin = Car.of("무민", 1);

		//when
		final int compare = junpak.compareTo(moomin);
		final boolean isSamePosition = junpak.isSamePosition(moomin);

		//then
		assertThat(compare).isSameAs(2 - 1);
		assertThat(isSamePosition).isFalse();
	}
}
