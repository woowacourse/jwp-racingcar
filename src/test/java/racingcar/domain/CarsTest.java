package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.utils.TestNumberGenerator;

@DisplayName("Cars 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CarsTest {

    private Cars cars;

    @BeforeEach
    void setUp() {
        cars = new Cars(List.of("car1", "car2", "car3"));
    }

    @Test
    void 생성자는_중복된_이름이_존재하는_목록을_입력받으면_예외를_던진다() {
        // given
        List<String> duplicatedNames = List.of("car1", "car1");

        // expect
        assertThatThrownBy(() -> new Cars(duplicatedNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 차 이름이 없어야 합니다.");
    }

    @Test
    void race_메서드는_자동차_경주를_1회_진행한다() {
        // given
        NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 5));

        // when
        cars.race(numberGenerator);

        // then
        assertThat(cars.getCars())
                .extracting(Car::getPosition)
                .containsExactly(1, 0, 1);
    }

    @Test
    void findWinners_메서드는_우승자_이름_목록을_반환한다() {
        // given
        NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 5));
        cars.race(numberGenerator);

        // when
        List<String> result = cars.findWinners();

        // then
        assertThat(result).containsExactly("car1", "car3");
    }

    @Test
    void findWinners_메서드는_우승자가_존재하지_않는_경우_예외를_던진다() {
        // given
        Cars emptyCars = new Cars(List.of());

        // expect
        assertThatThrownBy(emptyCars::findWinners)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("우승자가 존재하지 않습니다.");
    }

    @Test
    void getCars_메서드는_경주에_참가한_모든_차의_이름과_현재_위치를_반환한다() {
        // given
        NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 5));
        cars.race(numberGenerator);

        // when
        List<Car> result = cars.getCars();

        // then
        assertAll(
                () -> assertThat(result).extracting(Car::getName).containsExactly("car1", "car2", "car3"),
                () -> assertThat(result).extracting(Car::getPosition).containsExactly(1, 0, 1)
        );
    }
}
