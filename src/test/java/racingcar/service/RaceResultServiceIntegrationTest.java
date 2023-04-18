package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.service.dto.CarStatusResponse;
import racingcar.service.dto.GameInfoRequest;
import racingcar.service.dto.RaceResultResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("RaceResultService Integration Test")
class RaceResultServiceIntegrationTest {

    @Autowired
    private RaceResultService raceResultService;

    @Test
    @DisplayName("createRaceResult() : 게임 정보를 통해 새로운 게임을 만들 수 있다.")
    void test_createRaceResult() throws Exception {
        //given
        final GameInfoRequest gameInfoRequest = new GameInfoRequest("a,b,c,d", 4);

        //when
        final RaceResultResponse raceResult = raceResultService.createRaceResult(gameInfoRequest);

        //then
        final List<CarStatusResponse> racingCars = raceResult.getRacingCars();

        assertThat(racingCars).hasSize(4)
                              .extracting("name")
                              .containsExactly("a", "b", "c", "d");
    }

    @Test
    @DisplayName("searchRaceResult() : 모든 경기 결과를 조회할 수 있다.")
    void test_searchRaceResult() throws Exception {
        //when
        final List<RaceResultResponse> raceResultResponses = raceResultService.searchRaceResult();

        //then
        assertThat(raceResultResponses).extracting("winners")
                                       .hasSize(2)
                                       .containsExactly("빙봉", "a,b,c,d");
    }
}
