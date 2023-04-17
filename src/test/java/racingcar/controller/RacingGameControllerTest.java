package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.AlwaysMoveNumberGenerator;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@Import(value = AlwaysMoveNumberGenerator.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RacingGameControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private RacingGameDao racingGameDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        String sql = "DELETE FROM car";
        String sql2 = "DELETE FROM racing_game";

        jdbcTemplate.execute(sql);
        jdbcTemplate.execute(sql2);
    }

    @Test
    void playGame_success() {
        String names = "브리,토미,브라운";
        RacingGameRequest request = new RacingGameRequest(names, 10);
        RacingGameResponse response = new RacingGameResponse(names, null);
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", is(response.getWinners()));
    }

    @Test
    void playGame_fail() {
        RacingGameRequest request = new RacingGameRequest("브리,브리,브라운", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is("중복된 이름은 사용할 수 없습니다"));
    }

    @Test
    void getHistoriesTest() {
        setUpGame1();
        setUpGame2();

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2));
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
