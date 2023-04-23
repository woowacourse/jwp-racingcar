package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameResponse;


class RacingGameAddServiceTest {

    RacingGameAddService racingGameAddService;
    RacingGameDao racingGameDao;
    CarDao carDao;

    @BeforeEach
    void setUp() {
        carDao = Mockito.mock(CarDao.class);
        racingGameDao = Mockito.mock(RacingGameDao.class);
        racingGameAddService = new RacingGameAddService(carDao, racingGameDao);
    }

    @Test
    @DisplayName("이름과 실행 횟수를 받아 게임의 결과를 반환한다")
    void playTest() {
        RacingGame racingGame = RacingGame.of(List.of("박스터", "엔초"), 10);

        RacingGameResponse play = racingGameAddService.addGame(racingGame, 10);

        assertAll(
                () -> assertThat(play.getWinners()).isNotEmpty(),
                () -> assertThat(play.getRacingCars()).hasSize(2)
        );
    }
}
