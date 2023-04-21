package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.RacingCarGameResultDto;

@SpringBootTest
@Transactional
class RacingCarServiceTest {
    @Autowired
    private RacingCarService racingCarService;

    @DisplayName("플레이 시 게임 정보가 저장된다.")
    @Test
    void Should_Save_When_Play() {
        Cars cars = Cars.from(List.of("토리", "또링"));
        TryCount tryCount = new TryCount(1);

        RacingCarGameResultDto racingCarGameResultDto = racingCarService.play(cars, tryCount);

        assertThat(racingCarGameResultDto.getRacingCars()).hasSize(2);
    }

    @DisplayName("저장된 모든 정보를 가져온다.")
    @Test
    void Should_GetAllResult_When_GetAllResult() {
        Cars cars = Cars.from(List.of("토리", "또링"));
        TryCount tryCount = new TryCount(1);

        racingCarService.play(cars, tryCount);

        assertThat(racingCarService.getAllGameResult()).hasSize(1);
    }
}
