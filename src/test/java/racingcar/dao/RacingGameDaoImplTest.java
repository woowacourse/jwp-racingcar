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
import racingcar.model.Car;
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
        // given
        Car car = new Car("우가");
        Car car2 = new Car("케로");
        Car car3 = new Car("완태");

        RacingGame racingGame = new RacingGame(List.of(car, car2, car3), 3);

        // when
        racingGameDao.insertRacingGame(RacingGameDto.of(racingGame.getId(), racingGame.getTrialCount()));

        List<RacingGameEntity> racingGames = racingGameDao.selectAllResults();
        RacingGameEntity findRacingGame = racingGames.get(0);

        // then
        assertThat(findRacingGame.getId()).isGreaterThan(0);
        assertThat(findRacingGame.getTrialCount()).isEqualTo(racingGame.getTrialCount());
    }

    @Test
    void selectAllResultsTest() {
        // given
        Car car = new Car("우가");
        Car car2 = new Car("케로");
        Car car3 = new Car("완태");

        RacingGame racingGame1 = new RacingGame(List.of(car, car2, car3), 3);
        RacingGame racingGame2 = new RacingGame(List.of(car, car2, car3), 3);

        // when
        racingGameDao.insertRacingGame(RacingGameDto.of(racingGame1.getId(), racingGame1.getTrialCount()));
        racingGameDao.insertRacingGame(RacingGameDto.of(racingGame2.getId(), racingGame2.getTrialCount()));

        List<RacingGameEntity> racingGames = racingGameDao.selectAllResults();

        // then
        assertThat(racingGames).hasSize(2);
    }

}
