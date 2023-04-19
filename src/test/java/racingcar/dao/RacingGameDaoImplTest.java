package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.RacingGameDto;
import racingcar.model.RacingGame;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RacingGameDaoImplTest {

    @Autowired
    RacingGameDaoImpl racingGameDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void deleteAll() {
        jdbcTemplate.execute("delete from CAR");
        jdbcTemplate.execute("delete from RACING_GAME");
    }

    @Test
    void insertRacingGameTest() {
        RacingGame racingGame = new RacingGame(3);
        racingGameDao.insertRacingGame(RacingGameDto.of(racingGame.getId(), racingGame.getTrialCount()));

        List<RacingGameDto> racingGames = racingGameDao.selectAllResults();
        RacingGameDto findRacingGame = racingGames.get(0);

        assertThat(findRacingGame.getId()).isGreaterThan(0);
        assertThat(findRacingGame.getTrialCount()).isEqualTo(racingGame.getTrialCount());
    }

    @Test
    void selectAllResultsTest() {
        RacingGame racingGame1 = new RacingGame(3);
        RacingGame racingGame2 = new RacingGame(3);

        racingGameDao.insertRacingGame(RacingGameDto.of(racingGame1.getId(), racingGame1.getTrialCount()));
        racingGameDao.insertRacingGame(RacingGameDto.of(racingGame2.getId(), racingGame2.getTrialCount()));

        List<RacingGameDto> racingGames = racingGameDao.selectAllResults();

        assertThat(racingGames).hasSize(2);
    }

}
