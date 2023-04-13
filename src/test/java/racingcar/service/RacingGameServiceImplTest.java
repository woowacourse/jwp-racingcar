package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import racingcar.controller.dto.CarInfoDto;
import racingcar.controller.dto.RacingInfoResponse;
import racingcar.domain.CarGroup;

@SpringBootTest
class RacingGameServiceImplTest {

    @Autowired
    private RacingGameServiceImpl service;

    @DisplayName("자동차 경주를 통해 게임 결과를 반환한다.")
    @Test
    void race() {
        // given
        final String jeomoon = "저문";
        final String hyena = "헤나";
        final CarGroup carGroup = new CarGroup("저문,헤나");
        final int trial = 10;

        // when
        final RacingInfoResponse response = service.race(carGroup, trial);

        final List<String> names = response.getRacingCars()
                .stream()
                .map(CarInfoDto::getName)
                .collect(Collectors.toList());

        // then
        assertThat(names).contains(jeomoon, hyena);
    }
}
