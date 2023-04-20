package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.GameResultDAOInJdbc;
import racingcar.dao.PlayerResultDAOInJdbc;
import racingcar.dao.entity.GameResultEntity;
import racingcar.dao.entity.PlayerResultEntity;
import racingcar.domain.Names;
import racingcar.domain.TryCount;
import racingcar.dto.CarDto;
import racingcar.dto.response.GameResponseDto;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceTest {
    @Mock
    private GameResultDAOInJdbc gameResultDAOInJdbc;

    @Mock
    private PlayerResultDAOInJdbc playerResultDAOInJdbc;

    @InjectMocks
    private RacingGameService racingGameService;

    @DisplayName("게임을 play할 경우, 게임 결과를 반환한다.")
    @Test
    void playGameSuccessTest() {
        //given
        Names names = Names.from(List.of("쥬니", "도치"));
        TryCount tryCount = TryCount.from(10);

        when(gameResultDAOInJdbc.save(any()))
                .thenReturn(1);

        doNothing().when(playerResultDAOInJdbc)
                .saveAll(any());

        //when
        GameResponseDto result = racingGameService.play(names, tryCount);

        //then
        List<String> winners = List.of(result.getWinners().split(","));
        List<String> players = getPlayers(result);

        assertThat(List.of("쥬니", "도치")).containsAll(winners);
        assertThat(List.of("쥬니", "도치")).containsExactlyElementsOf(players);
    }

    @DisplayName("데이터베이스에 저장된 모든 게임 및 플레이어 결과를 가져올 수 있다.")
    @Test
    void findAllGameAndPlayerResultsTest() {
        //given
        int gameId = 1;
        when(gameResultDAOInJdbc.findAll())
                .thenReturn(List.of(GameResultEntity.of(gameId, 10, LocalTime.now())));

        when(playerResultDAOInJdbc.findAll())
                .thenReturn(List.of(PlayerResultEntity.of(1, "쥬니", 2, gameId)));

        //when
        GameResponseDto gameResponseDto = racingGameService.findAllGameAndPlayerResults().get(0);

        List<String> players = getPlayers(gameResponseDto);
        List<String> winners = List.of(gameResponseDto.getWinners().split(","));
        int position = gameResponseDto.getRacingCars().get(0).getPosition();

        //then
        assertThat(players).containsExactly("쥬니");
        assertThat(winners).containsExactly("쥬니");
        assertThat(position).isEqualTo(2);
    }

    private List<String> getPlayers(GameResponseDto gameResponseDto) {
        return gameResponseDto.getRacingCars().stream()
                .map(CarDto::getName)
                .collect(Collectors.toList());
    }
}
