package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.GameJdbcDao;
import racingcar.dao.PlayerJdbcDao;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.entity.Game;
import racingcar.entity.Player;
import racingcar.utils.DrivableNumberGenerator;

public class RacingCarServiceTest {

    private RacingCarService racingCarService;
    private GameJdbcDao gameDao;
    private PlayerJdbcDao playerDao;

    @BeforeEach
    void setUp() {
        gameDao = Mockito.mock(GameJdbcDao.class);
        playerDao = Mockito.mock(PlayerJdbcDao.class);
        racingCarService = new RacingCarService(gameDao, playerDao, new DrivableNumberGenerator());
    }

    @Test
    void 게임을_진행한다() {
        PlayRequest playRequest = new PlayRequest(List.of("비버", "허브", "애쉬", "박스더"), 4);
        PlayResponse playResponse = racingCarService.play(playRequest);

        assertAll(
                () -> assertThat(playResponse.getWinners()).isEqualTo("비버,허브,애쉬,박스더"),
                () -> assertThat(playResponse.getRacingCars()).hasSize(4)
        );
    }

    @Test
    void 이력을_조회한다() {
        given(gameDao.findAll()).willReturn(List.of(
                new Game(1, "비버,허브", 10, LocalDateTime.now()),
                new Game(2, "애쉬", 5, LocalDateTime.now())
        ));
        given(playerDao.findAll()).willReturn(List.of(
                new Player(1, "비버", 5, 1),
                new Player(2, "허브", 5, 1),
                new Player(3, "애쉬", 3, 1),
                new Player(4, "애쉬", 3, 2)
        ));

        List<PlayResponse> responses = racingCarService.findHistory();

        assertAll(
                () -> assertThat(responses).hasSize(2),
                () -> assertThat(responses.get(0).getWinners()).isEqualTo("비버,허브"),
                () -> assertThat(responses.get(0).getRacingCars()).extracting("name")
                        .containsExactlyInAnyOrder("비버", "허브", "애쉬"),
                () -> assertThat(responses.get(1).getWinners()).isEqualTo("애쉬"),
                () -> assertThat(responses.get(1).getRacingCars()).extracting("name")
                        .containsExactlyInAnyOrder("애쉬"));
    }
}
