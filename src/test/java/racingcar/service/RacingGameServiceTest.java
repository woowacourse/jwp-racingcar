package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.CarGroup;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SpringBootTest
class RacingGameServiceTest {

    @Autowired
    private RacingGameService racingGameService;

    @DisplayName("자동차 경주를 통해 게임 결과를 반환한다.")
    @Test
    void race() {
        // given
        final String jeomoon = "저문";
        final String hyena = "헤나";
        final CarGroup carGroup = new CarGroup("저문,헤나");
        final int trial = 10;

        // when
        final RacingGameResponse response = racingGameService.race(carGroup, trial);

        final List<String> names = response.getRacingCars()
                .stream()
                .map(CarDto::getName)
                .collect(Collectors.toList());

        // then
        assertThat(names).contains(jeomoon, hyena);
    }

    @DisplayName("모든 자동차 경주 게임을 진행했던 결과를 반환한다.")
    @Test
    void findHistory() {
        // given
        String firstCarNames = "저문,헤나";
        String secondCarNames = "저문,헤나,디노,우가";

        final CarGroup firstCarGroup = new CarGroup(firstCarNames);
        final CarGroup secondCarGroup = new CarGroup(secondCarNames);
        final int trial = 10;

        racingGameService.race(firstCarGroup, trial);
        racingGameService.race(secondCarGroup, trial);

        // when
        List<RacingGameResponse> racingGameResponses = racingGameService.findHistory();

        String firstGroupWinner = String.join(",", racingGameResponses.get(0).getWinners().split(","));
        String secondGroupWinner = String.join(",", racingGameResponses.get(1).getWinners().split(","));

        // then
        assertSoftly(softly -> {
            softly.assertThat(racingGameResponses.size()).isEqualTo(2);
            softly.assertThat(firstCarNames).contains(firstGroupWinner);
            softly.assertThat(secondCarNames).contains(secondGroupWinner);
        });
    }
}
