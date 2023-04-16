package racingcar.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;
import racingcar.model.Car;

@JdbcTest
@Sql(scripts = {"classpath:data.sql"})
class RacingCarDaoTest {
    
    private final RowMapper<Car> actorRowMapper = (resultSet, rowNum) -> {
        return new Car(
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
    };
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingCarDao carDao;
    
    @BeforeEach
    void setUp() {
        this.carDao = new RacingCarDao(this.jdbcTemplate);
        //create dummy game data
        final String sql = "INSERT INTO racing_game(trial_count, winners) VALUES (?,?)";
        this.jdbcTemplate.update(sql, 5, "io");
    }
    
    
    @Test
    @DisplayName("insert - 자동차 객체와 게임 아이디를 받아서 DB에 저장한다.")
    void insertCarTest() {
        final Car car = new Car("echo", 5);
        final int gameId = 1;
        
        this.carDao.insert(car, gameId);
        final String sql = "SELECT * FROM racing_car WHERE racing_game_id = ? AND name = ?";
        final Car carFromDB = this.jdbcTemplate.queryForObject(sql, this.actorRowMapper, gameId, car.getName());
        assertThat(carFromDB.getName()).isEqualTo(car.getName());
        
    }
    
    @Test
    @DisplayName("isert - 여러 자동차 객체와 게임 아이디를 받아서 DB에 저장한다.")
    void findCarsByGameIdTest() {
        final int gameId = 1;
        final Car car = new Car("io", 5);
        this.carDao.insert(car, gameId);
        final Car car2 = new Car("echo", 5);
        this.carDao.insert(car2, gameId);
        final String sql = "SELECT * FROM racing_car WHERE racing_game_id = ?";
        final List<Car> cars = this.jdbcTemplate.query(sql, this.actorRowMapper, gameId);
        assertThat(cars.size()).isEqualTo(2);
    }
    
}