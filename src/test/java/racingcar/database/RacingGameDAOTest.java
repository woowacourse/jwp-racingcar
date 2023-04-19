package racingcar.database;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import racingcar.car.interfaces.Car;
import racingcar.car.model.RacingCar;
import racingcar.game.model.RacingCarGameResult;
import racingcar.game.repository.RacingGameDAO;

@JdbcTest
@Import(RacingGameDAO.class)
@Sql(scripts = {"classpath:data.sql"})
class RacingGameDAOTest {
    
    @Autowired
    private RacingGameDAO gameDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @BeforeEach
    void setUp() {
        this.gameDao = new RacingGameDAO(this.jdbcTemplate);
    }
    
    @Test
    @DisplayName("insert - 게임 횟수와 우승자 이름을 받아서 DB에 저장한다.")
    void insertGameTest() {
        //given
        final int trialCount = 5;
        final Car echo = RacingCar.create("echo", 1);
        final Car io = RacingCar.create("io", 0);
        final RacingCarGameResult gameResult = RacingCarGameResult.create(List.of(echo), List.of(echo, io));
        final String winners = gameResult.getWinners();
        final String sql = "SELECT count(*) FROM racing_game WHERE trial_count = ? AND winners = ?";
        final int beforeCount = this.jdbcTemplate.queryForObject(sql, Integer.class, trialCount, winners);
        
        //when
        this.gameDao.insert(trialCount, gameResult);
        this.gameDao.insert(trialCount, gameResult);
        
        //then
        final int afterCount = this.jdbcTemplate.queryForObject(sql, Integer.class, trialCount, winners);
        Assertions.assertThat(afterCount - beforeCount).isEqualTo(2);
    }
    
    @Test
    @DisplayName("insert - 가장 최근에 저장된 게임의 아이디를 반환한다.")
    void keyTest() {
        //given
        final int trialCount = 5;
        final Car echo = RacingCar.create("echo", 1);
        final Car io = RacingCar.create("io", 0);
        final RacingCarGameResult gameResult = RacingCarGameResult.create(List.of(echo), List.of(echo, io));
        
        //when
        final int firstKey = this.gameDao.insert(trialCount, gameResult);
        final int secondKey = this.gameDao.insert(trialCount, gameResult);
        
        //then
        Assertions.assertThat(secondKey - firstKey).isEqualTo(1);
    }
}