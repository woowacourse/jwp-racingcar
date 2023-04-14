package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarResponse;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.mock.MockCarDao;
import racingcar.mock.MockRaceResultDao;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RaceServiceTest {

    private RaceService raceService;

    @BeforeEach
    void init() {
        raceService = new RaceService(() -> 1, new MockCarDao(), new MockRaceResultDao());
    }

    @Test
    @DisplayName("경주를 진행하면 사용자가 입력한 시도 횟수만큼 전체 결과를 생성하고, 자동차의 개수만큼 경주 결과를 생성한다.")
    void play() {
        // given
        final String testCarNames = "pobi,crong,honux";
        final int raceCount = 2;
        final RaceRequest raceRequest = new RaceRequest(testCarNames, raceCount);

        // when
        final RaceResponse raceResults = raceService.play(raceRequest);

        // then
        assertAll(() -> assertThat(raceResults.getWinners())
                        .isEqualTo(testCarNames),
                () -> assertThat(raceResults.getRacingCars().size())
                        .isEqualTo(3));
    }

    @Test
    @DisplayName("경주 결과를 반환한다")
    void getRaceResult() {
        // given
        final List<RaceResponse> expected = List.of(RaceResponse.create("pobi",
                List.of(new CarResponse("pobi", 10))));

        // when
        final List<RaceResponse> actual = raceService.getRaceResult();

        // then
        assertAll(() -> assertThat(actual.size())
                        .isEqualTo(expected.size()),
                () -> assertThat(actual.get(0).getWinners())
                        .isEqualTo(expected.get(0).getWinners()));
    }
}
