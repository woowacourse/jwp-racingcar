package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dao.MemoryRacingCarDao;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResultDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RacingCarServiceTest {
    private RacingCarService racingCarService = new RacingCarService(new MemoryRacingCarDao());

    @DisplayName("차들의 이름과 시도 횟수를 입력 받아서 우승자와 차들을 반환한다")
    @Test
    void playRequest() {
        //given
        final PlayRequestDto dto = new PlayRequestDto("a, b, c", 10);

        //when
        final PlayResultDto result = racingCarService.play(dto);

        //then
        assertAll(
                () -> assertThat(result.getRacingCars()).hasSize(3),
                () -> assertThat(result.getWinners()).isNotEmpty());
    }
}
