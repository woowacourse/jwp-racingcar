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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlayerResultDaoImplTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    PlayResultDao playResultDaoImpl;

    @Autowired
    PlayerResultDao playerResultDaoImpl;

    private int playResultId;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void initialize() {
        jdbcTemplate.execute("delete from PLAYER_RESULT");
        jdbcTemplate.execute("delete from PLAY_RESULT");
        playResultId = playResultDaoImpl.insertPlayResult(new PlayResult("name1,name2", 3));
    }

    @Test
    void insertPlayer() {
        PlayerResult playerResult = new PlayerResult(playResultId, "name", 10);
        playerResultDaoImpl.insertPlayerResult(playerResult);

        List<PlayerResult> playerResults = playerResultDaoImpl.selectPlayerResult(playResultId);
        PlayerResult findedPlayerResult = playerResults.get(0);

        assertThat(findedPlayerResult.getName()).isEqualTo(playerResult.getName());
    }

    @Test
    void selectPlayerResultByPlayResultIdTest() {
        PlayerResult playerResult = new PlayerResult(playResultId, "name1", 10);
        PlayerResult playerResult2 = new PlayerResult(playResultId, "name2", 10);
        playerResultDaoImpl.insertPlayerResult(playerResult);
        playerResultDaoImpl.insertPlayerResult(playerResult2);

        List<PlayerResult> playerResults = playerResultDaoImpl.selectPlayerResult(playResultId);

        assertThat(playerResults.size()).isEqualTo(2);
    }

}
