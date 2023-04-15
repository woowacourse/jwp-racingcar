package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import racingcar.service.PlayerResult;
import racingcar.service.RacingResult;

@SpringBootTest
@Transactional
class PlayerResultDaoTest {

    @Autowired
    RacingResultDao racingResultDao;

    @Autowired
    PlayerResultDao playerResultDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RacingResult racingResult;

    @BeforeEach
    void initialize(){
        racingResult = racingResultDao.insertRacingResult(new RacingResult("name1,name2", 3));
    }

    @Test
    @DisplayName("playerResult 1건이 제대로 db에 insert되는지 확인")
    void insertPlayer(){
        // given
        PlayerResult playerResult = new PlayerResult(racingResult.getId(), "name", 10);
        playerResultDao.insertPlayer(playerResult);
        List<PlayerResult> playerResults = playerResultDao.selectPlayerResultByRacingResultId(2);

        // when
        PlayerResult findedPlayerResult = playerResults.get(0);

        // then
        assertThat(findedPlayerResult.getName()).isEqualTo(playerResult.getName());
    }

    @Test
    @DisplayName("playerResult를 두 번 insert했을 때 모든 playerResult를 제대로 select하는 확인")
    void selectPlayerResultByPlayResultIdTest(){
        // given
        PlayerResult playerResult1 = new PlayerResult(racingResult.getId(), "name1", 10);
        PlayerResult playerResult2 = new PlayerResult(racingResult.getId(), "name2", 10);
        playerResultDao.insertPlayer(playerResult1);
        playerResultDao.insertPlayer(playerResult2);

        // when
        List<PlayerResult> playerResults = playerResultDao.selectPlayerResultByRacingResultId(racingResult.getId());

        // then
        assertThat(playerResults.size()).isEqualTo(2);
        assertThat(playerResults.containsAll(List.of(playerResult1, playerResult2))).isTrue();
    }

}
