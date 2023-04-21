package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import racingcar.service.RacingResult;

@SpringBootTest
@Transactional
class JdbcGameDaoTest {

    @Autowired
    JdbcGameDao jdbcGameDao;

    @Test
    @DisplayName("RacingResult 1건이 제대로 insert되었는지 확인")
    void insertRacingResultTest() {
        // given
        RacingResult racingResult = new RacingResult("name1,name2", 3);
        jdbcGameDao.insertRacingResult(racingResult);
        List<RacingResult> racingResults = jdbcGameDao.selectAllResults();

        // when
        RacingResult findPlayResult = racingResults.get(0);

        // then
        assertThat(findPlayResult.getId()).isEqualTo(racingResult.getId());
        assertThat(findPlayResult.getWinners()).isEqualTo(racingResult.getWinners());
        assertThat(findPlayResult.getTrialCount()).isEqualTo(racingResult.getTrialCount());
    }

    @Test
    @DisplayName("RacingResult를 두 번 insert했을 때 모든 RacingResult를 제대로 select하는 확인")
    void selectAllResultsTest() {
        // given
        RacingResult racingResult1 = new RacingResult("name1,name2", 3);
        RacingResult racingResult2 = new RacingResult("name3,name4", 4);

        jdbcGameDao.insertRacingResult(racingResult1);
        jdbcGameDao.insertRacingResult(racingResult2);

        // when
        List<RacingResult> racingResults = jdbcGameDao.selectAllResults();

        // then
        assertThat(racingResults.size()).isEqualTo(2);
        assertThat(racingResults.containsAll(List.of(racingResult1, racingResult2))).isTrue();
    }

}
