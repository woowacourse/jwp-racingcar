package racingcar.service;

import org.junit.jupiter.api.Test;
import racingcar.domain.numbergenerator.NumberGenerator;
import racingcar.service.RacingCarService.GameResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarServiceTest {

    private RacingCarService racingCarService = new RacingCarService(new NumberGenerator() {
        private int count = 0;
        @Override
        public int generateNumber() {
            if (count++ % 3 == 0) {
                return 9;
            }
            return 1;
        }
    });

    @Test
    void playGame() {
        GameResult gameResult = racingCarService.play(List.of("브리", "토미", "브라운"), 10);

        assertThat(gameResult.getWinners()).isEqualTo("브리");
        assertThat(gameResult.getRacingCars().size()).isEqualTo(3);
    }
}
