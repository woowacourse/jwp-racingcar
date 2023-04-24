package racingcar.domain.vo;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

	@Test
	@DisplayName("move 실행 시, 1만큼 큰 Postion 객체를 반환한다.")
	void moveTest() {
		// given
		final Position position1 = new Position(1);
		final Position position2 = new Position(2);

		// when
		final Position movePosition = position1.move();

		// then
		assertThat(position2.isSamePosition(movePosition)).isTrue();
		assertThat(position2.getValue()).isSameAs(movePosition.getValue());
		assertThat(new HashSet<>(List.of(movePosition, position2))).hasSize(1);
	}

	@Test
	@DisplayName("두 Position 객체를 compareTo 하면, 두 값의 차에 해당하는 정수를 반환한다.")
	void compareToTest() {
		// given
		final int one = 1;
		final int two = 2;

		final Position position1 = new Position(one);
		final Position position2 = new Position(two);

		// when
		final int compare = position2.compareTo(position1);

		// then
		assertThat(compare).isEqualTo(two - one);
	}

}
