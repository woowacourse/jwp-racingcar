package racingcar.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.numbergenerator.NumberGenerator;
import racingcar.application.RacingGameService.GameResult;

import javax.annotation.Priority;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("RacingGameService 은")
@SpringBootTest
@Import(value = RacingGameServiceTest.MockNumberGenerator.class)
@Transactional
class RacingGameServiceTest {

    @Autowired
    private RacingGameService racingGameService;

    @Priority(1)
    @TestComponent
    public static class MockNumberGenerator implements NumberGenerator {

        private int count = 0;

        @Override
        public int generateNumber() {
            if (count++ % 3 == 0) {
                return 9;
            }
            return 1;
        }
    }

    @Test
    void 게임을_진행한다() {
        // when
        final Long id = racingGameService.play(List.of("브리", "토미", "브라운"), 10);

        // then
        final GameResult gameResult = racingGameService.findResultById(id);
        assertThat(gameResult.getWinners()).containsExactly("브리");
        assertThat(gameResult.getRacingCars().size()).isEqualTo(3);
    }
}
