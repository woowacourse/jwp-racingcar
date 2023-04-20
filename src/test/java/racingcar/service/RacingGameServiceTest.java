package racingcar.service;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.dao.PlayerDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.mapper.PlayerDtoMapper;
import racingcar.dao.mapper.RacingGameDtoMapper;
import racingcar.domain.CarGroup;

class RacingGameServiceTest {

    private RacingGameService racingGameService;
    private PlayerDao playerDao;
    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        racingGameDao = Mockito.mock(RacingGameDao.class);
        playerDao = Mockito.mock(PlayerDao.class);
        racingGameService = new RacingGameService(racingGameDao, playerDao);
    }

    @DisplayName("자동차 경주를 통해 게임 결과를 반환한다.")
    @Test
    void race() {
        // given
        String jeomoon = "저문";
        String hyena = "헤나";
        CarGroup carGroup = new CarGroup("저문,헤나");
        int trial = 10;
        int racingGameId = 1;

        given(racingGameDao.save(jeomoon, trial)).willReturn(racingGameId);
        given(playerDao.save(carGroup, racingGameId)).willReturn(true);

        // when
        RacingGameResponse racingGameResponse = racingGameService.race(carGroup, trial);
        List<String> names = racingGameResponse.getRacingCars()
                .stream()
                .map(CarDto::getName)
                .collect(Collectors.toList());

        // then
        assertSoftly(softly -> {
            softly.assertThat(racingGameResponse.getWinners()).isEqualTo(jeomoon);
            softly.assertThat(names).contains(jeomoon, hyena);
        });
    }

    @DisplayName("모든 자동차 경주 게임을 진행했던 결과를 반환한다.")
    @Test
    void findHistory() {
        // given
        int firstGameId = 1;
        int secondGameId = 2;

        given(racingGameDao.findAll()).willReturn(
                List.of(
                        new RacingGameDtoMapper(firstGameId, "헤나"),
                        new RacingGameDtoMapper(secondGameId, "디노,우가")
                )
        );
        given(playerDao.findAllById(firstGameId)).willReturn(
                List.of(
                        new PlayerDtoMapper("저문", 3, firstGameId),
                        new PlayerDtoMapper("헤나", 7, firstGameId)
                )
        );
        given(playerDao.findAllById(secondGameId)).willReturn(
                List.of(
                        new PlayerDtoMapper("저문", 6, secondGameId),
                        new PlayerDtoMapper("헤나", 4, secondGameId),
                        new PlayerDtoMapper("디노", 8, secondGameId),
                        new PlayerDtoMapper("우가", 8, secondGameId)
                )
        );

        // when
        List<RacingGameResponse> racingGameResponses = racingGameService.findHistory();

        // then
        assertSoftly(softly -> {
            softly.assertThat(racingGameResponses).hasSize(2);
            softly.assertThat(racingGameResponses.get(0).getWinners()).isEqualTo("헤나");
            softly.assertThat(racingGameResponses.get(1).getWinners()).isEqualTo("디노,우가");
            softly.assertThat(racingGameResponses.get(0).getRacingCars().get(0).getName()).isEqualTo("저문");
            softly.assertThat(racingGameResponses.get(0).getRacingCars().get(1).getName()).isEqualTo("헤나");
            softly.assertThat(racingGameResponses.get(1).getRacingCars().get(0).getName()).isEqualTo("저문");
            softly.assertThat(racingGameResponses.get(1).getRacingCars().get(1).getName()).isEqualTo("헤나");
            softly.assertThat(racingGameResponses.get(1).getRacingCars().get(2).getName()).isEqualTo("디노");
            softly.assertThat(racingGameResponses.get(1).getRacingCars().get(3).getName()).isEqualTo("우가");
        });
    }
}
