package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.RacingCarResult;
import racingcar.model.TestCarNumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

class RacingCarServiceTest {

    @Test
    @DisplayName("자동차 경주 진행 테스트")
    void playRacingCarTest() {
        final List<String> names = List.of("io", "oi");
        final int trialCount = 3;

        final TestCarNumberGenerator testCarNumberGenerator = new TestCarNumberGenerator(newArrayList(9, 1, 1, 1, 1, 1));

        final RacingCarService racingCarService = new RacingCarService(testCarNumberGenerator);

        final RacingCarResult racingCarResult = racingCarService.playRacingCar(names, trialCount);
        assertThat(racingCarResult.getWinners()).isEqualTo(List.of("io"));
    }
}
