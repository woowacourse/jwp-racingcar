package racingcar.service;

import org.assertj.core.api.RecursiveComparisonAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.Player;
import racingcar.dao.PlayerDao;
import racingcar.dao.RacingGameDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

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
        final Map<Long, List<Player>> results = racingGameService.findAll();

        // then
        assertAll(
                () -> assertThat(results).hasSize(2),
                () -> assertPlayers(results, 1L).isEqualTo(
                        List.of(
                                new Player(1L, "콩하나", 10, true),
                                new Player(1L, "에단", 5, false))),
                () -> assertPlayers(results, 2L).isEqualTo(
                        List.of(new Player(2L, "콩하나", 9, true),
                                new Player(2L, "소니", 9, true),
                                new Player(2L, "에단", 9, true))
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

    private RecursiveComparisonAssert<?> assertPlayers(Map<Long, List<Player>> results, long key) {
        return assertThat(results.get(key))
                .usingRecursiveComparison()
                .ignoringFields("id");
    }
}
