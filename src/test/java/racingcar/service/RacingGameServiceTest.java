package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.controller.dto.GameResponse;
import racingcar.controller.dto.RacingCarDto;
import racingcar.dao.Player;
import racingcar.dao.PlayerDao;
import racingcar.dao.RacingGameDao;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceTest {

    @InjectMocks
    private RacingGameService racingGameService;

    @Mock
    private RacingGameDao racingGameDao;

    @Mock
    private PlayerDao playerDao;

    @Test
    @DisplayName("findAll()을 테스트한다.")
    void findAll_whenCall_thenReturnMap() {
        // given
        when(playerDao.findAll()).thenReturn(createPlayers());

        // when
        final List<GameResponse> responses = racingGameService.findAll();

        // then
        assertAll(
                () -> assertThat(responses).hasSize(2),
                () -> assertThat(responses.get(0).getRacingCars())
                        .usingRecursiveComparison()
                        .isEqualTo(List.of(
                                        new RacingCarDto("콩하나", 10),
                                        new RacingCarDto("에단", 5)
                                )
                        ),
                () -> assertThat(responses.get(1).getRacingCars())
                        .usingRecursiveComparison()
                        .isEqualTo(List.of(
                                        new RacingCarDto("콩하나", 9),
                                        new RacingCarDto("소니", 9),
                                        new RacingCarDto("에단", 9)
                                )
                        )
        );
    }

    private List<Player> createPlayers() {
        final List<Player> players = new ArrayList<>();
        players.add(new Player(1L, "콩하나", 10, true));
        players.add(new Player(1L, "에단", 5, false));
        players.add(new Player(2L, "콩하나", 9, true));
        players.add(new Player(2L, "소니", 9, true));
        players.add(new Player(2L, "에단", 9, true));
        return players;
    }

}
