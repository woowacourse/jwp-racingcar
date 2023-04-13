package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.RaceResult;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.mock.MockNumberGenerator;
import racingcar.provider.TestProvider;
import racingcar.repository.CarRaceRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RaceServiceTest {

    private RaceService raceService;

    @BeforeEach
    void init() {
        final MockNumberGenerator numberGenerator = TestProvider.createMockNumberGenerator(false);
        raceService = new RaceService(numberGenerator, new TestCarRaceRepository());
    }

    @Test
    @DisplayName("경주를 진행하면 사용자가 입력한 시도 횟수만큼 전체 결과를 생성하고, 자동차의 개수만큼 경주 결과를 생성한다.")
    void givenRaceCount_whenStart_thenReturnResultAboutRaceCount() {
        // given
        final String testCarNames = "pobi,crong,honux";
        final int raceCount = 2;
        final RaceRequest raceRequest = new RaceRequest(testCarNames, raceCount);

        // when
        final RaceResponse raceResults = raceService.play(raceRequest);

        // then
        assertThat(raceResults.getWinners())
                .isEqualTo(testCarNames);

        assertThat(raceResults.getRacingCars().size())
                .isEqualTo(3);
    }

    private static class TestCarRaceRepository implements CarRaceRepository {

        @Override
        public void save(final RaceResult raceResult) {

        }

        @Override
        public List<RaceEntity> findRaceEntities() {
            return null;
        }

        @Override
        public List<CarEntity> findCarEntities(final Long raceResultId) {
            return null;
        }
    }
}
