package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.PlaysRequest;
import racingcar.dto.PlaysResponse;
import racingcar.utils.DrivableNumberGenerator;

public class RacingCarServiceTest {

    @Test
    void 게임을_진행한다() {
        GameDao gameDao = Mockito.mock(GameDao.class);
        PlayerDao playerDao = Mockito.mock(PlayerDao.class);

        RacingCarService racingCarService = new RacingCarService(gameDao, playerDao, new DrivableNumberGenerator());

        PlaysRequest playsRequest = new PlaysRequest("비버,허브,애쉬,박스더", 4);
        PlaysResponse playsResponse = racingCarService.play(playsRequest);

        assertAll(
                () -> assertThat(playsResponse.getWinners()).isEqualTo("비버,허브,애쉬,박스더"),
                () -> assertThat(playsResponse.getRacingCars()).hasSize(4)
        );
    }
}
