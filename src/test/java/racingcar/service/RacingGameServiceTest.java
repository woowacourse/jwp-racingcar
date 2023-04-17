package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.RacingGameEntity;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;


class RacingGameServiceTest {

    RacingGameService racingGameService;
    RacingGameDao racingGameDao;
    CarDao carDao;

    @BeforeEach
    void setUp() {
        carDao = Mockito.mock(CarDao.class);
        racingGameDao = Mockito.mock(RacingGameDao.class);
        racingGameService = new RacingGameService(carDao, racingGameDao);
    }

    @Test
    @DisplayName("이름과 실행 횟수를 받아 게임의 결과를 반환한다")
    void playTest() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("박스터,엔초", 10);

        RacingGameResponse play = racingGameService.play(racingGameRequest);

        assertAll(
                () -> assertThat(play.getWinners()).isNotEmpty(),
                () -> assertThat(play.getRacingCars()).hasSize(2)
        );
    }

    @Test
    @DisplayName("전체 결과를 조회하여 결과를 반환한다")
    void findHistory() {
        given(racingGameDao.findAllByCreatedTimeAsc())
                .willReturn(List.of(new RacingGameEntity(10)));
        given(carDao.findByRacingGameId(any()))
                .willReturn(List.of(new CarEntity("박스터", 7, false, 1L),
                        new CarEntity("현구막", 10, true, 1L)));

        List<RacingGameResponse> history = racingGameService.findHistory();

        assertAll(
                () -> assertThat(history).hasSize(1),
                () -> assertThat(history.get(0).getRacingCars()).hasSize(2),
                () -> assertThat(history.get(0).getWinners()).isEqualTo("현구막")
        );
    }
}
