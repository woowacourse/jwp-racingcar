package racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.domain.vo.Name;
import racingcar.strategy.RacingNumberGenerator;

class CarsTest {

	@Test
	@DisplayName("중복된 이름 입력 시, 예외가 발생한다")
	void validateDuplicateName() {
		//given
		final List<String> carNames = List.of("준팍", "무민", "준팍", "빙봉", "검프");

		// when & then
		assertThatThrownBy(() -> Cars.from(carNames))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이름은 중복될 수 없습니다.");
	}

	@Test
	@DisplayName("findWinnerNames 메서드 사용 시 우승자 이름들을 리턴한다.")
	void findWinnerNamesTest() {
		// given
		final List<String> carNames = List.of("준팍", "무민", "빙봉", "검프");
		final Cars cars = Cars.from(carNames);
		final RacingNumberGenerator generator = new TestNumberGenerator(List.of(0, 3, 6, 9));

		// when
		cars.race(generator);

		// then
		assertThat(cars.findWinnerNames()).isEqualTo(List.of(new Name(carNames.get(2)), new Name(carNames.get(3))));
	}
}
