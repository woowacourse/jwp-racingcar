package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dao.RacingConsoleDao;
import racingcar.model.car.Cars;

class RacingServiceTest {

    RacingService racingService = new RacingService(new RacingConsoleDao());

    @Test
    @DisplayName("자동차 게임이 정상적으로 작동한다.")
    void playSuccess() {
        String names = "그레이,호이,로건";
        String trialTimes = "10";

        final Cars finishedCars = racingService.play(names, trialTimes);

        assertThat(finishedCars.getCarsCurrentInfo()).isNotNull();
        assertThat(finishedCars.getWinnerCars()).isNotNull();
    }
}
