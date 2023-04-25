package racingcar.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;
import racingcar.car.model.Car;
import racingcar.car.model.CarName;
import racingcar.car.model.CarPosition;
import racingcar.car.model.RacingCar;
import racingcar.car.repository.CarDAO;
import racingcar.car.repository.RacingCarDAO;

@JdbcTest
@Import(RacingCarDAO.class)
@Sql(scripts = {"classpath:data.sql"})
class RacingCarDAOTest {
    
    private final RowMapper<Car> actorRowMapper = (resultSet, rowNum) -> new RacingCar(
            CarName.create(resultSet.getString("name")),
            CarPosition.create(resultSet.getInt("position"))
    );
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CarDAO racingCarDAO;
    
    @BeforeEach
    void setUp() {
        //create dummy game data
        final String sql = "INSERT INTO racing_game(trial_count, winners) VALUES (?,?)";
        this.jdbcTemplate.update(sql, 5, "io");
        
    }
    
    @Test
    @DisplayName("insert - 자동차 객체와 게임 아이디를 받아서 DB에 저장한다.")
    void insertCarTest() {
        //given
        final Car car = RacingCar.create("io", 5);
        final int gameId = 1;
        
        //when
        this.racingCarDAO.insert(car, gameId);
        
        //then
        final String sql = "SELECT * FROM racing_car WHERE racing_game_id = ? AND name = ?";
        final Car carFromDB = this.jdbcTemplate.queryForObject(sql, this.actorRowMapper, gameId,
                car.getName().getValue());
        assertThat(carFromDB.getName()).isEqualTo(car.getName());
        
    }
    
    @Test
    @DisplayName("isert - 여러 자동차 객체와 게임 아이디를 받아서 DB에 저장한다.")
    void findCarsByGameIdTest() {
        //given
        final int gameId = 1;
        final Car car = RacingCar.create("io", 5);
        final Car car2 = RacingCar.create("echo", 5);
        
        //when
        this.racingCarDAO.insert(car, gameId);
        this.racingCarDAO.insert(car2, gameId);
        
        //then
        final String sql = "SELECT * FROM racing_car WHERE racing_game_id = ?";
        final List<Car> cars = this.jdbcTemplate.query(sql, this.actorRowMapper, gameId);
        assertThat(cars.size()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("findByName - 자동차 이름과 게임 아이디를 받아서 DB에서 자동차 객체를 찾아서 반환한다.")
    void findByNameTest() {
        //given
        final String name = "io";
        final int gameId = 1;
        final Car car = RacingCar.create(name, 5);
        this.racingCarDAO.insert(car, gameId);
        
        //when
        final Car carResult = this.racingCarDAO.findByName(name, gameId);
        
        //then
        assertThat(carResult.getName().getValue()).isEqualTo(name);
    }
    
    @Test
    @DisplayName("findAll - 게임 아이디를 받아서 DB에서 모든 자동차를 찾아서 반환한다.")
    void findAllByGameIdTest() {
        //given
        final int gameId = 1;
        final Car car = RacingCar.create("io", 5);
        final Car car2 = RacingCar.create("echo", 5);
        this.racingCarDAO.insert(car, gameId);
        this.racingCarDAO.insert(car2, gameId);
        
        //when
        final List<Car> cars = this.racingCarDAO.findAll(gameId);
        
        //then
        assertThat(cars.size()).isEqualTo(2);
        assertThat(cars.get(0).getName().getValue()).isEqualTo("io");
        assertThat(cars.get(1).getName().getValue()).isEqualTo("echo");
    }
    
    
}
