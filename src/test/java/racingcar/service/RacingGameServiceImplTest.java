package racingcar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.numbergenerator.NumberGenerator;
import racingcar.dto.GameResultDto;

import javax.annotation.Priority;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(value = RacingGameServiceImplTest.MockNumberGenerator.class)
@Transactional
class RacingGameServiceImplTest {

    @Autowired
    private RacingGameServiceImpl racingGameService;

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
    void playGame() {
        GameResultDto gameResult = racingGameService.play(List.of("브리", "토미", "브라운"), 10);

        Assertions.assertAll(
                () -> assertThat(gameResult.getWinners()).isEqualTo("브리"),
                () -> assertThat(gameResult.getRacingCars()).hasSize(3)
        );
    }
}
