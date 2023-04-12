package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.service.PlayResult;

@SpringBootTest
class PlayResultDaoTest {

    @Autowired
    PlayResultDao playResultDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void initialize(){
        String deletePlayResultSql = "delete from play_result";
        jdbcTemplate.execute(deletePlayResultSql);
    }

    @Test
    void insertPlayResultTest(){
        PlayResult playResult = new PlayResult("name1,name2", 3);
        playResultDao.insertPlayResult(playResult);

        List<PlayResult> playResults = playResultDao.selectAllResults();
        PlayResult findPlayResul = playResults.get(0);

        assertThat(findPlayResul.getId()).isEqualTo(playResult.getId());
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
