package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import racingcar.service.RacingResult;

@SpringBootTest
@Transactional
class RacingResultDaoTest {

    @Autowired
    RacingResultDao racingResultDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void insertPlayResultTest(){
        RacingResult racingResult = new RacingResult("name1,name2", 3);
        racingResultDao.insertPlayResult(racingResult);

        List<RacingResult> racingResults = racingResultDao.selectAllResults();
        RacingResult findPlayResul = racingResults.get(0);

        assertThat(findPlayResul.getId()).isEqualTo(racingResult.getId());
        assertThat(findPlayResul.getWinners()).isEqualTo(racingResult.getWinners());
        assertThat(findPlayResul.getTrialCount()).isEqualTo(racingResult.getTrialCount());
    }

    @Test
    void selectAllResultsTest() {
        RacingResult racingResult1 = new RacingResult("name1,name2", 3);
        RacingResult racingResult2 = new RacingResult("name1,name2", 3);

        racingResultDao.insertPlayResult(racingResult1);
        racingResultDao.insertPlayResult(racingResult2);

        List<RacingResult> racingResults = racingResultDao.selectAllResults();

        assertThat(racingResults.size()).isEqualTo(2);
    }

}
