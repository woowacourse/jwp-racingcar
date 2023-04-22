package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayResultJdbcDao;
import racingcar.dao.PlayerDao;
import racingcar.dao.PlayerJdbcDao;
import racingcar.dto.GameDto;
import racingcar.dto.GameResponse;
import racingcar.dto.WinnerCarDto;
import racingcar.entity.PlayResult;
import racingcar.entity.Player;
import racingcar.strategy.RacingTestNumberGenerator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class CarServiceTest {

    private CarService carService;
    private PlayResultDao playResultDao;
    private PlayerDao playerDao;

    @BeforeEach
    void setUp() {
        playResultDao = Mockito.mock(PlayResultJdbcDao.class);
        playerDao = Mockito.mock(PlayerJdbcDao.class);
        carService = new CarService(new RacingTestNumberGenerator(List.of(4, 1, 2)), playerDao, playResultDao);
    }

    @Test
    void playGame() {
        final GameDto gameDto = new GameDto("무민, 준팍", "1");

        final WinnerCarDto winnerCarDto = carService.playGame(gameDto);

        assertEquals("무민", winnerCarDto.getWinners().get(0).getName());
        assertEquals(2, winnerCarDto.getRacingCars().size());
    }

    @Test
    void findPlayHistories() {
        final long playerResultID = 1L;
        final List<PlayResult> playResults = List.of(new PlayResult(playerResultID));
        final List<Player> players = List.of(
                new Player(playerResultID, "무민", 2, true),
                new Player(playerResultID, "준팍", 1, false)
        );
        given(playResultDao.findAllPlayResult()).willReturn(playResults);
        given(playerDao.findAllPlayer(playerResultID)).willReturn(players);

        final List<GameResponse> playHistories = carService.findPlayHistories();

        final GameResponse gameResponse = playHistories.get(0);
        assertEquals(1, playHistories.size());
        assertEquals("무민", gameResponse.getWinners());
        assertEquals(2, gameResponse.getRacingCars().size());
    }
}