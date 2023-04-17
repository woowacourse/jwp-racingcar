package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.request.GameSaveDto;
import racingcar.dto.response.GameWinnerDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
@Import(GameDao.class)
class GameDaoTest {

    @Autowired
    GameDao gameDao;

    @Test
    @Transactional
    void createGameTest() {
        GameSaveDto gameDto = new GameSaveDto("leo", 10);
        Long gameId = gameDao.createGame(gameDto);

        List<GameWinnerDto> allGames = gameDao.findAllGames();
        assertThat(allGames.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void findAllGameTest() {
        GameSaveDto game1 = new GameSaveDto("leo", 10);
        GameSaveDto game2 = new GameSaveDto("aaa", 6);

        gameDao.createGame(game1);
        gameDao.createGame(game2);

        List<GameWinnerDto> allGames = gameDao.findAllGames();
        GameWinnerDto secondGameResult = allGames.get(1);
        assertAll(
                () -> assertThat(allGames.size()).isEqualTo(2),
                () -> assertThat(secondGameResult.getWinners()).isEqualTo(game2.getWinners())
        );

    }
}
