package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;

@SpringBootTest
class RaceServiceTest {

    @Autowired
    private RaceService raceService;

    @Test
    @DisplayName("게임 정보를 받아서 게임 결과를 저장한다.")
    void saveRaceResult() {
        // given
        GameInfoRequest gameInfoRequest = new GameInfoRequest("성하,이오,코코닥", 5);

        // when
        int savedResultId = raceService.saveRaceResult(gameInfoRequest);

        // then
        assertThat(savedResultId).isNotNull();
    }

    @Test
    @DisplayName("게임 결과를 생성한다.")
    void createRaceResult() {
        // given
        GameInfoRequest gameInfoRequest = new GameInfoRequest("성하,이오,코코닥", 5);
        int playResultId = raceService.saveRaceResult(gameInfoRequest);

        // when
        RaceResultResponse raceResultResponse = raceService.createRaceResult(playResultId);

        // then
        assertThat(raceResultResponse.getRacingCars()).isNotNull();
        assertThat(raceResultResponse.getWinners()).isNotNull();
    }

    @Test
    @DisplayName("게임 결과를 조회한다.")
    void searchAllRaceResult() {
        // given
        GameInfoRequest gameInfoRequest1 = new GameInfoRequest("성하,이오,코코닥", 5);
        GameInfoRequest gameInfoRequest2 = new GameInfoRequest("a,b,c", 5);
        raceService.saveRaceResult(gameInfoRequest1);
        raceService.saveRaceResult(gameInfoRequest2);

        // when
        List<RaceResultResponse> raceResultResponses = raceService.searchAllRaceResult();

        // then
        assertThat(raceResultResponses.size()).isEqualTo(2);
    }
}
