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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
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

    private int playResultId;

    @BeforeEach
    void initialize() {
        playResultId = playResultDao.insertPlayResult(new PlayResult("name1,name2", 3));
    }

    @Test
    void insertPlayer() {
        PlayerResult playerResult = new PlayerResult(playResultId, "name", 10);
        playerResultDao.insertPlayerResult(playerResult);

        List<PlayerResult> playerResults = playerResultDao.selectPlayerResult(playResultId);
        PlayerResult findedPlayerResult = playerResults.get(0);

        assertThat(findedPlayerResult.getName()).isEqualTo(playerResult.getName());
    }

    @Test
    void selectPlayerResultByPlayResultIdTest() {
        PlayerResult playerResult = new PlayerResult(playResultId, "name1", 10);
        PlayerResult playerResult2 = new PlayerResult(playResultId, "name2", 10);
        playerResultDao.insertPlayerResult(playerResult);
        playerResultDao.insertPlayerResult(playerResult2);

        List<PlayerResult> playerResults = playerResultDao.selectPlayerResult(playResultId);

        assertThat(playerResults.size()).isEqualTo(2);
    }

}
