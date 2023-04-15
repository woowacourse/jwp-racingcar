package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RacingGameJdbcDaoTest {

    @Autowired
    private RacingGameDao racingGameDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void insert() {
        String winners = "win1,win2";
        int trialCount = 5;
        Long gameId = racingGameDao.save(winners, trialCount);

        String sqlForWinners = "SELECT winners FROM racing_game WHERE id = " + gameId;
        String sqlForTrialCount = "SELECT trial_count FROM racing_game WHERE id = " + gameId;

        Integer savedTrialCount = jdbcTemplate.queryForObject(sqlForTrialCount, Integer.class);
        String savedWinners = jdbcTemplate.queryForObject(sqlForWinners, String.class);

        assertThat(trialCount).isEqualTo(savedTrialCount);
        assertThat(winners).isEqualTo(savedWinners);
    }

    @Test
    void loadHistoriesTest() {
        setUpGame1();
        setUpGame2();

        List<RacingGameResponse> racingGameResponses = racingGameDao.loadHistories(carDao);
        assertThat(racingGameResponses).hasSize(2);

        RacingGameResponse game1 = racingGameResponses.get(0);
        assertThat(game1.getWinners()).isEqualTo("car2,car3");

        RacingGameResponse game2 = racingGameResponses.get(1);
        assertThat(game2.getWinners()).isEqualTo("car3");
    }

    private void setUpGame1() {
        CarDto car1 = CarDto.of("car1", 10);
        CarDto car2 = CarDto.of("car2", 11);
        CarDto car3 = CarDto.of("car3", 11);
        Long gameId = racingGameDao.save("car2,car3", 15);
        carDao.saveAll(gameId, List.of(car1, car2, car3));
    }

    private void setUpGame2() {
        CarDto car1 = CarDto.of("car1", 5);
        CarDto car2 = CarDto.of("car2", 7);
        CarDto car3 = CarDto.of("car3", 9);
        Long gameId = racingGameDao.save("car3", 10);
        carDao.saveAll(gameId, List.of(car1, car2, car3));
    }
}
