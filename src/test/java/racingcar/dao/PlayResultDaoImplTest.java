package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlayResultDaoImplTest {

    @Autowired
    PlayResultDaoImpl playResultDaoImpl;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void deleteAll() {
        jdbcTemplate.execute("delete from PLAYER_RESULT");
        jdbcTemplate.execute("delete from PLAY_RESULT");
    }

    @Test
    void insertPlayResultTest() {
        PlayResult playResult = new PlayResult("name1,name2", 3);
        playResultDaoImpl.insertPlayResult(playResult);

        List<PlayResult> playResults = playResultDaoImpl.selectAllResults();
        PlayResult findPlayResul = playResults.get(0);

        assertThat(findPlayResul.getId()).isGreaterThan(0);
        assertThat(findPlayResul.getWinners()).isEqualTo(playResult.getWinners());
        assertThat(findPlayResul.getTrialCount()).isEqualTo(playResult.getTrialCount());
    }

    @Test
    void selectAllResultsTest() {
        PlayResult playResult1 = new PlayResult("name1,name2", 3);
        PlayResult playResult2 = new PlayResult("name1,name2", 3);

        playResultDaoImpl.insertPlayResult(playResult1);
        playResultDaoImpl.insertPlayResult(playResult2);

        List<PlayResult> playResults = playResultDaoImpl.selectAllResults();

        assertThat(playResults.size()).isEqualTo(2);
    }

}
