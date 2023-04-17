package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.GameResultDAO;
import racingcar.dao.PlayerResultDAO;
import racingcar.dto.CarDto;
import racingcar.dto.response.GameResponseDto;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceTest {
    @Mock
    private GameResultDAO gameResultDAO;

    @Mock
    private PlayerResultDAO playerResultDAO;

    @InjectMocks
    private RacingGameService racingGameService;

    @DisplayName("중복된 이름이 존재하는 경우, 예외가 발생한다.")
    @Test
    void playGameWithDuplicateNames() {
        assertThatThrownBy(() -> racingGameService.play(List.of("쥬니", "쥬니"), 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름의 길이가 1글자 이하, 5글자 초과인 경우, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"쥬니쥬니쥬니", "1234556789", ""})
    void playGameWithInvalidTryCount(String name) {
        assertThatThrownBy(() -> racingGameService.play(List.of(name), 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("허용되지 않은 시도 횟수인 경우, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0})
    void playGameWithInvalidTryCount(int tryCount) {
        assertThatThrownBy(() -> racingGameService.play(List.of("쥬니", "도치"), tryCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임을 play할 경우, 게임 결과를 반환한다.")
    @Test
    void playGameSuccessTest() {
        //given
        List<String> names = List.of("쥬니", "도치");
        int tryCount = 10;

        when(gameResultDAO.save(any()))
                .thenReturn(1);

        doNothing().when(playerResultDAO)
                .saveAll(anyList());

        //when
        GameResponseDto result = racingGameService.play(names, tryCount);

        //then
        List<String> winners = List.of(result.getWinners().split(","));
        List<String> players = getPlayers(result);

        assertThat(names).containsAll(winners);
        assertThat(names).containsExactlyElementsOf(players);
    }

    private List<String> getPlayers(GameResponseDto gameResponseDto) {
        return gameResponseDto.getRacingCars().stream()
                .map(CarDto::getName)
                .collect(Collectors.toList());
    }
}
