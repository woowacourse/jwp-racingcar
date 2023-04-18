package racing;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomNumberGeneratorTest {

    @DisplayName("1부터 10까지 랜덤한 숫자가 나오는지 검증한다.")
    @Test
    void generate() {
        // given
        RandomNumberGenerator generator = new RandomNumberGenerator();

        // when, then
        Assertions.assertThat(generator.generate()).isBetween(0, 10);
    }

}
