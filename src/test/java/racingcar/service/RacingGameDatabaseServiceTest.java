package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RacingGameDatabaseServiceTest {
    @Mock
    RacingGameService racingGameService;

    @DisplayName("자동차 경주를 통해 게임 결과를 저장한 값을 반환한다.")
    @Test
    void race() {
        // given
        final RacingGameResponse response
                = new RacingGameResponse("헤나", List.of(new Car("헤나"), new Car("찰리")));

        when(racingGameService.race(any(), anyInt())).thenReturn(response);

        // when
        final RacingGameResponse racingGameResponse = racingGameService.race(List.of("헤나", "찰리"), 10);

        // then
        assertThat(racingGameResponse)
                .usingRecursiveComparison()
                .isEqualTo(response);
    }

    @DisplayName("모든 레이싱 게임 결과를 조회한다.")
    @Test
    void findAllRacingGameHistories() {
        // given
        final RacingGameResponse response
                = new RacingGameResponse("헤나", List.of(new Car("헤나"), new Car("찰리")));

        when(racingGameService.findAllRacingGameHistories()).thenReturn(List.of(response));

        // when
        final List<RacingGameResponse> findGameHistories = racingGameService.findAllRacingGameHistories();
        final RacingGameResponse findGameHistory = findGameHistories.get(0);

        final List<String> findCarNameValues = findGameHistory.getRacingCars()
                .stream()
                .map(CarDto::getName)
                .collect(Collectors.toList());

        // then
        assertAll(
                () -> assertThat(findGameHistory.getWinners()).isEqualTo("헤나"),
                () -> assertThat(findCarNameValues).containsExactly("헤나", "찰리")
        );
    }
}
