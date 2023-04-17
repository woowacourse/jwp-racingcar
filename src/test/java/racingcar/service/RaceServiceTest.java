package racingcar.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.domain.NumberGenerator;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.RaceResultDao;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {

    @Mock
    private NumberGenerator numberGenerator;

    @Mock
    private CarDao carDao;

    @Mock
    private RaceResultDao raceResultDao;

    @InjectMocks
    private RaceService raceService;

    @Test
    @DisplayName("경주를 진행하면 사용자가 입력한 시도 횟수만큼 전체 결과를 생성하고, 자동차의 개수만큼 경주 결과를 생성한다.")
    void testPlay() {
        // given
        final String testCarNames = "pobi,crong,honux";
        final int raceCount = 2;
        final RaceRequest raceRequest = new RaceRequest(testCarNames, raceCount);
        given(raceResultDao.save(anyInt(), anyString()))
            .willReturn(1L);

        // when
        final RaceResponse result = raceService.play(raceRequest);

        // then
        assertThat(result.getWinners())
            .isEqualTo(testCarNames);
        assertThat(result.getRacingCars().size())
            .isEqualTo(3);
    }

    @Test
    @DisplayName("모든 경주 기록 조회한다.")
    void testFindAllRace() {
        // given
        final RaceEntity raceEntity1 = new RaceEntity(1L, 1, "test");
        final RaceEntity raceEntity2 = new RaceEntity(2L, 2, "test");
        final List<RaceEntity> raceEntities = List.of(raceEntity1, raceEntity2);
        final CarEntity carEntity1 = new CarEntity(1L, "test", 1);
        final CarEntity carEntity2 = new CarEntity(2L, "test", 2);
        final List<CarEntity> carEntities = List.of(carEntity1, carEntity2);
        given(raceResultDao.findAll())
            .willReturn(raceEntities);
        given(carDao.findAll(anyLong()))
            .willReturn(carEntities);

        // when
        final List<RaceResponse> result = raceService.findAllRace();

        // then
        assertThat(result.size()).isEqualTo(raceEntities.size());
        for (int i = 0; i < raceEntities.size(); i++) {
            assertThat(result.get(i).getWinners()).isEqualTo(raceEntities.get(i).getWinners());
            assertThat(result.get(i).getRacingCars().get(i).getName()).isEqualTo(
                carEntities.get(i).getName());
        }
    }
}
