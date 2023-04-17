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
import racingcar.service.PlayResult;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlayResultDaoTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    PlayResultDao playResultDao;

    @Test
    void insertPlayResultTest() {
        PlayResult playResult = new PlayResult("name1,name2", 3);
        playResultDao.insertPlayResult(playResult);

        List<PlayResult> playResults = playResultDao.selectAllResults();
        PlayResult findPlayResul = playResults.get(0);

        assertThat(findPlayResul.getId()).isGreaterThan(0);
        assertThat(findPlayResul.getWinners()).isEqualTo(playResult.getWinners());
        assertThat(findPlayResul.getTrialCount()).isEqualTo(playResult.getTrialCount());
    }

    @Test
    void selectAllResultsTest() {
        PlayResult playResult1 = new PlayResult("name1,name2", 3);
        PlayResult playResult2 = new PlayResult("name1,name2", 3);

        playResultDao.insertPlayResult(playResult1);
        playResultDao.insertPlayResult(playResult2);

        List<PlayResult> playResults = playResultDao.selectAllResults();

        assertThat(playResults.size()).isEqualTo(2);
    }

}
