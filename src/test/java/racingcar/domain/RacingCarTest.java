package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingCarTest {

    @ParameterizedTest
    @CsvSource(value = {"9:2", "1:1"}, delimiter = ':')
    public void 자동차_전진_테스트(int number, int position) {
        RacingCar racingCar = new RacingCar("오잉");
        racingCar.advance(number);
        int actual = racingCar.getPosition();
        assertThat(actual).isEqualTo(position);
    }
}
