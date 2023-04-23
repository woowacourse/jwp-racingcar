package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;
import racingcar.model.Car;
import racingcar.model.RacingGame;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CarDaoImplTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    RacingGameDao racingGameDaoImpl;

    @Autowired
    CarDao carDaoImpl;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void initialize() {
        jdbcTemplate.execute("delete from CAR");
        jdbcTemplate.execute("delete from RACING_GAME");
    }

    @Test
    void insertPlayer() {
        // given
        Car actual = new Car("우가");
        RacingGame racingGame = new RacingGame(List.of(actual), 10);
        RacingGameEntity racingGameEntity = racingGameDaoImpl.insertRacingGame(
                RacingGameDto.from(racingGame.getTrialCount()));

        // when
        carDaoImpl.insertCar(makeCarDto(actual), racingGameEntity.getId());
        List<CarEntity> carsByRacingGameId = carDaoImpl.findCarsByRacingGameId(racingGameEntity.getId());

        // then
        assertThat(carsByRacingGameId.get(0).getName()).isEqualTo(actual.getName());
    }

    @Test
    void selectPlayerResultByPlayResultIdTest() {
        // given
        Car car = new Car("name1", 10);
        Car car2 = new Car("name2", 10);

        RacingGame racingGame = new RacingGame(List.of(car, car2), 10);
        RacingGameEntity racingGameEntity = racingGameDaoImpl.insertRacingGame(
                RacingGameDto.from(racingGame.getTrialCount()));

        // when
        carDaoImpl.insertCar(makeCarDto(car), racingGameEntity.getId());
        carDaoImpl.insertCar(makeCarDto(car2), racingGameEntity.getId());

        List<CarEntity> carsByRacingGameId = carDaoImpl.findCarsByRacingGameId(racingGameEntity.getId());

        // then
        assertThat(carsByRacingGameId).hasSize(2);
    }

    private CarDto makeCarDto(final Car actual) {
        return CarDto.of(actual.getName(), actual.getPosition());
    }

}
