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
import racingcar.dao.web.WebCarDao;
import racingcar.dao.web.WebGameDao;
import racingcar.domain.Car;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WebCarDaoTest {

    @Autowired
    private WebGameDao webGameDao;

    @Autowired
    private WebCarDao webCarDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Car car = new Car("밀리", 1);
    private final Car other = new Car("조이", 1);
    private int gameId;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute(DROP_TABLE_CAR);
        jdbcTemplate.execute(DROP_TABLE_GAME);
        jdbcTemplate.execute(CREATE_TABLE_GAME);
        jdbcTemplate.execute(CREATE_TABLE_CAR);

        gameId = webGameDao.insertGame(5);
        webCarDao.insertCars(List.of(car, other), gameId);
    }

    @Test
    @DisplayName("car를 저장한다.")
    void insertCars() {
        webCarDao.insertCars(List.of(new Car("포비", 1)), gameId);
        assertThat(webCarDao.findCars(gameId))
                .hasSize(3)
                .extracting(Car::name)
                .contains("포비");
    }

    @Test
    @DisplayName("모든 사람의 position을 업데이트한다.")
    void updatePosition() {
        webCarDao.updatePositions(
                List.of(new Car("밀리", 5), new Car("조이", 6)),
                gameId);
        List<Car> cars = webCarDao.findCars(gameId);

        assertAll(
                () -> assertThat(cars)
                        .extracting(Car::name)
                        .containsExactly("밀리", "조이"),

                () -> assertThat(cars)
                        .extracting(Car::position)
                        .containsExactly(5, 6)
        );
    }

    @Test
    @DisplayName("우승자를 업데이트한다.")
    void updateWinner() {
        webCarDao.updateWinners(List.of(car), gameId);
        List<Car> cars = webCarDao.findWinners(gameId);

        assertAll(
                () -> assertThat(cars)
                        .extracting(Car::name)
                        .containsExactly("밀리")
        );
    }

    @Test
    @DisplayName("우승자를 조회한다.")
    void findWinners() {
        webCarDao.updateWinners(List.of(car), gameId);
        List<Car> winners = webCarDao.findWinners(gameId);

        assertThat(winners)
                .hasSize(1)
                .extracting(Car::name)
                .containsExactly("밀리");
    }

    @Test
    @DisplayName("전체 자동차를 조회한다.")
    void findCars() {
        Car car = webCarDao.findCars(gameId).get(0);
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
