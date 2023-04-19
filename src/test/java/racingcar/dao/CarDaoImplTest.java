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

    private RacingGameDto racingGameDto;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void initialize() {
        jdbcTemplate.execute("delete from CAR");
        jdbcTemplate.execute("delete from RACING_GAME");
        RacingGame racingGame = new RacingGame(10);
        racingGameDto = racingGameDaoImpl.insertRacingGame(RacingGameDto.from(racingGame.getTrialCount()));
    }

    @Test
    void insertPlayer() {
        Car actual = new Car("우가");
        carDaoImpl.insertCar(makeCarDto(actual), racingGameDto.getId());
        List<CarDto> carsByRacingGameId = carDaoImpl.findCarsByRacingGameId(racingGameDto.getId());

        assertThat(carsByRacingGameId.get(0).getName()).isEqualTo(actual.getName());
    }

    @Test
    void selectPlayerResultByPlayResultIdTest() {
        Car car = new Car("name1", 10);
        Car car2 = new Car("name2", 10);
        carDaoImpl.insertCar(makeCarDto(car), racingGameDto.getId());
        carDaoImpl.insertCar(makeCarDto(car2), racingGameDto.getId());

        List<CarDto> carsByRacingGameId = carDaoImpl.findCarsByRacingGameId(racingGameDto.getId());

        assertThat(carsByRacingGameId).hasSize(2);
    }

    private CarDto makeCarDto(final Car actual) {
        return CarDto.of(actual.getName(), actual.getPosition());
    }

}
