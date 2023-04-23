package racingcar.database;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import racingcar.car.model.Car;
import racingcar.car.model.RacingCar;
import racingcar.game.model.GameResult;
import racingcar.game.model.RacingCarGameResult;
import racingcar.game.repository.GameDAO;

@JdbcTest
@Import(GameTestConfiguration.class)
@Sql(scripts = {"classpath:data.sql"})
class RacingCarGameDAOTest {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private GameDAO racingCarGameDAO;
    
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
        this.racingCarGameDAO.insert(trialCount, gameResult);
        this.racingCarGameDAO.insert(trialCount, gameResult);
        
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
        final int firstKey = this.racingCarGameDAO.insert(trialCount, gameResult);
        final int secondKey = this.racingCarGameDAO.insert(trialCount, gameResult);
        
        //then
        Assertions.assertThat(secondKey - firstKey).isEqualTo(1);
    }
    
    @Test
    @DisplayName("find - 게임 아이디를 받아서 게임 결과를 반환한다.")
    void findGameTest() {
        //given
        final int trialCount = 5;
        final Car echo = RacingCar.create("echo", 1);
        final Car io = RacingCar.create("io", 0);
        final RacingCarGameResult gameResult = RacingCarGameResult.create(List.of(echo), List.of(echo, io));
        final int gameId = this.racingCarGameDAO.insert(trialCount, gameResult);
        
        //when
        final GameResult gameResultFromDB = this.racingCarGameDAO.find(gameId);
        
        //then
        Assertions.assertThat(gameResultFromDB.getWinners()).isEqualTo(gameResult.getWinners());
        Assertions.assertThat(gameResultFromDB.getWinners()).isEqualTo(gameResult.getWinners());
    }
    
    
    @Test
    @DisplayName("findAll - 모든 게임 결과를 반환한다.")
    void findAllGameTest() {
        //given
        final int trialCount = 5;
        final Car echo = RacingCar.create("echo", 1);
        final Car io = RacingCar.create("io", 0);
        final RacingCarGameResult gameResult = RacingCarGameResult.create(List.of(echo), List.of(echo, io));
        this.racingCarGameDAO.insert(trialCount, gameResult);
        this.racingCarGameDAO.insert(trialCount, gameResult);
        
        //when
        final List<GameResult> gameResults = this.racingCarGameDAO.findAll();
        
        //then
        Assertions.assertThat(gameResults.size()).isEqualTo(2);
    }
}