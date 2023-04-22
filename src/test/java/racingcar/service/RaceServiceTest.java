package racingcar.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.domain.NumberGenerator;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceResultEntity;
import racingcar.domain.repository.RaceResultEntityRepository;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {

    @Mock
    private NumberGenerator numberGenerator;

    @Mock
    private RaceResultEntityRepository raceResultEntityRepository;

    @InjectMocks
    private RaceService raceService;

    @Test
    @DisplayName("경주를 진행하면 사용자가 입력한 시도 횟수만큼 전체 결과를 생성하고, 자동차의 개수만큼 경주 결과를 생성한다.")
    void testPlay() {
        // given
        final String testCarNames = "pobi,crong,honux";
        final int raceCount = 2;
        final RaceRequest raceRequest = new RaceRequest(testCarNames, raceCount);

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
        final CarEntity carEntity1 = new CarEntity(1L, "test", 1);
        final CarEntity carEntity2 = new CarEntity(2L, "test", 2);
        final List<CarEntity> carEntities = List.of(carEntity1, carEntity2);
        final RaceResultEntity raceResultEntity1 = new RaceResultEntity(1L, 1, "test", carEntities);
        final RaceResultEntity raceResultEntity2 = new RaceResultEntity(2L, 2, "test", carEntities);
        final List<RaceResultEntity> raceEntities = List.of(raceResultEntity1, raceResultEntity2);
        given(raceResultEntityRepository.findAll())
            .willReturn(raceEntities);

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
