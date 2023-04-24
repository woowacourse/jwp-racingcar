package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static racingcar.constant.RacingCarDDL.CREATE_TABLE_CAR;
import static racingcar.constant.RacingCarDDL.CREATE_TABLE_GAME;
import static racingcar.constant.RacingCarDDL.DROP_TABLE_CAR;
import static racingcar.constant.RacingCarDDL.DROP_TABLE_GAME;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CarDaoTest {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Car car = new Car("밀리", 1);
    private int gameId;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute(DROP_TABLE_CAR);
        jdbcTemplate.execute(DROP_TABLE_GAME);
        jdbcTemplate.execute(CREATE_TABLE_GAME);
        jdbcTemplate.execute(CREATE_TABLE_CAR);

        gameId = gameDao.insertGame(5);
        carDao.insertCar(car, gameId);
    }

    @Test
    @DisplayName("car를 저장한다.")
    void insertCar() {
        int carId = carDao.insertCar(new Car("조이", 1), gameId);
        assertThat(carId).isEqualTo(2);
    }

    @Test
    @DisplayName("position을 업데이트한다.")
    void updatePosition() {
        carDao.updatePosition(new Car("밀리", 5), gameId);
        List<Car> cars = carDao.findCars(gameId);
        Car car = cars.get(0);

        assertAll(
                () -> assertThat(car)
                        .extracting(Car::name)
                        .isEqualTo("밀리"),

                () -> assertThat(car)
                        .extracting(Car::position)
                        .isEqualTo(5)
        );
    }

    @Test
    @DisplayName("우승자를 업데이트한다.")
    void updateWinner() {
        carDao.updateWinner("밀리", gameId);
        Car winner = carDao.findWinners(gameId).get(0);

        assertThat(winner.name()).isEqualTo("밀리");
    }

    @Test
    @DisplayName("우승자를 조회한다.")
    void findWinners() {
        carDao.insertCar(new Car("조이", 1), gameId);
        carDao.updateWinner("밀리", gameId);

        assertThat(carDao.findWinners(gameId)).hasSize(1);
    }

    @Test
    @DisplayName("전체 자동차를 조회한다.")
    void findCars() {
        Car car = carDao.findCars(gameId).get(0);
        assertAll(
                () -> assertThat(car)
                        .extracting(Car::name)
                        .isEqualTo("밀리"),

                () -> assertThat(car)
                        .extracting(Car::position)
                        .isEqualTo(1)
        );
    }
}
