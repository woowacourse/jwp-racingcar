package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import racingcar.domain.NumberGenerator;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.RaceResultDao;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;
import racingcar.dto.CarResponse;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(RaceService.class)
class RaceServiceTest {

    @MockBean
    private NumberGenerator numberGenerator;

    @MockBean
    private RaceResultDao raceResultDao;

    @MockBean
    private CarDao carDao;

    @Autowired
    private RaceService raceService;

    @Test
    @DisplayName("경주를 진행하면 사용자가 입력한 시도 횟수만큼 전체 결과를 생성하고, 자동차의 개수만큼 경주 결과를 생성한다.")
    void play() {
        // given
        final String testCarNames = "pobi,crong,honux";
        final int raceCount = 2;
        final RaceRequest raceRequest = new RaceRequest(testCarNames, raceCount);

        when(raceResultDao.save(anyInt(), any())).thenReturn(1L);
        doNothing().when(carDao).saveAll(any(), any());

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
        when(raceResultDao.findAll()).thenReturn(List.of(new RaceEntity(1L, 10, "pobi")));
        when(carDao.findAll(any())).thenReturn(List.of(new CarEntity(1L, "pobi", 10),
                new CarEntity(2L, "crong", 5),
                new CarEntity(3L, "honux", 3)));

        // when
        final List<RaceResponse> actual = raceService.getRaceResult();

        // then
        assertAll(() -> assertThat(actual.size())
                        .isEqualTo(expected.size()),
                () -> assertThat(actual.get(0).getWinners())
                        .isEqualTo(expected.get(0).getWinners()));
    }
}
