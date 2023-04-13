package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.restassured.RestAssured;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.service.PlayResult;
import racingcar.service.PlayerResult;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlayerResultDaoTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    PlayResultDao playResultDao;

    @Autowired
    PlayerResultDao playerResultDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private PlayResult playResult;

    @BeforeEach
    void initialize(){
        String deletePlayerResultSql = "delete from player_result";
        jdbcTemplate.execute(deletePlayerResultSql);

        String deletePlayResultSql = "delete from play_result";
        jdbcTemplate.execute(deletePlayResultSql);

        playResult = playResultDao.insertPlayResult(new PlayResult("name1,name2", 3));
    }

    @Test
    void insertPlayer(){
        PlayerResult playerResult = new PlayerResult(playResult.getId(), "name", 10);
        playerResultDao.insertPlayer(playerResult);

        List<PlayerResult> playerResults = playerResultDao.selectPlayerResultByPlayResultId(playResult.getId());
        PlayerResult findedPlayerResult = playerResults.get(0);

        assertThat(findedPlayerResult.getName()).isEqualTo(playerResult.getName());
    }

    @Test
    void selectPlayerResultByPlayResultIdTest(){
        PlayerResult playerResult = new PlayerResult(playResult.getId(), "name1", 10);
        PlayerResult playerResult2 = new PlayerResult(playResult.getId(), "name2", 10);
        playerResultDao.insertPlayer(playerResult);
        playerResultDao.insertPlayer(playerResult2);

        List<PlayerResult> playerResults = playerResultDao.selectPlayerResultByPlayResultId(playResult.getId());

        assertThat(playerResults.size()).isEqualTo(2);
    }

}
