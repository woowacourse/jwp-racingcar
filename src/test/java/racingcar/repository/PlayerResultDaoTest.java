package racingcar.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;
import racingcar.repository.dto.GetPlayerResultQueryResponseDto;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class PlayerResultDaoTest {

    @Autowired
    private DataSource dataSource;

    private GameDao gameDao;
    private PlayerResultDao playerResultDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao(dataSource);
        playerResultDao = new PlayerResultDao(dataSource);
    }

    @Test
    @DisplayName("플레이어 결과가 DB에 잘 저장되는지 확인")
    void save() {
        // given
        final Game game = gameDao.save(new Game(10, "디투"));
        final PlayerResult playerResult = new PlayerResult("디투", 8, game.getId());

        // when
        final PlayerResult savedPlayerResult = playerResultDao.save(playerResult);

        // then
        assertThat(savedPlayerResult.getName()).isEqualTo(playerResult.getName());
        assertThat(savedPlayerResult.getFinalPosition()).isEqualTo(playerResult.getFinalPosition());
        assertThat(savedPlayerResult.getGameId()).isEqualTo(playerResult.getGameId());
        assertThat(savedPlayerResult.getId()).isNotNull();
    }

    @Test
    @DisplayName("전체 게임 결과를 DB에서 잘 불러오는지 확인")
    void getAll() {
        // given
        final Game game1 = gameDao.save(new Game(10, "디투"));
        final PlayerResult playerResult1 = playerResultDao.save(new PlayerResult("디투", 8, game1.getId()));
        final PlayerResult playerResult2 = playerResultDao.save(new PlayerResult("레오", 6, game1.getId()));

        final Game game2 = gameDao.save(new Game(5, "디투,홍실"));
        final PlayerResult playerResult3 = playerResultDao.save(new PlayerResult("디투", 4, game2.getId()));
        final PlayerResult playerResult4 = playerResultDao.save(new PlayerResult("블랙캣", 3, game2.getId()));
        final PlayerResult playerResult5 = playerResultDao.save(new PlayerResult("홍실", 4, game2.getId()));
        final PlayerResult playerResult6 = playerResultDao.save(new PlayerResult("에단", 3, game2.getId()));

        // when
        final List<GetPlayerResultQueryResponseDto> queryResponses = playerResultDao.getAll();

        // then
        assertThat(queryResponses.size()).isEqualTo(6);

        assertThat(queryResponses.get(0).getWinners()).isEqualTo(game1.getWinners());
        assertThat(queryResponses.get(1).getWinners()).isEqualTo(game1.getWinners());
        assertThat(queryResponses.get(2).getWinners()).isEqualTo(game2.getWinners());
        assertThat(queryResponses.get(5).getWinners()).isEqualTo(game2.getWinners());

        assertThat(queryResponses.get(0).getName()).isEqualTo(playerResult1.getName());
        assertThat(queryResponses.get(1).getName()).isEqualTo(playerResult2.getName());
        assertThat(queryResponses.get(2).getName()).isEqualTo(playerResult3.getName());
        assertThat(queryResponses.get(3).getName()).isEqualTo(playerResult4.getName());
        assertThat(queryResponses.get(4).getName()).isEqualTo(playerResult5.getName());
        assertThat(queryResponses.get(5).getName()).isEqualTo(playerResult6.getName());
    }
}