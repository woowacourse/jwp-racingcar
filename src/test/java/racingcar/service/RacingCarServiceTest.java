package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.utils.DrivableNumberGenerator;

public class RacingCarServiceTest {

    @Test
    void 게임을_진행한다() {
        GameDao gameDao = Mockito.mock(GameDao.class);
        PlayerDao playerDao = Mockito.mock(PlayerDao.class);

        RacingCarService racingCarService = new RacingCarService(gameDao, playerDao, new DrivableNumberGenerator());

        PlayRequest playRequest = new PlayRequest("비버,허브,애쉬,박스더", 4);
        PlayResponse playResponse = racingCarService.play(playRequest);

        assertAll(
                () -> assertThat(playResponse.getWinners()).isEqualTo("비버,허브,애쉬,박스더"),
                () -> assertThat(playResponse.getRacingCars()).hasSize(4)
        );
    }
}
