package racingcar.controller;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.dto.RacingCarRequestDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarControllerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        jdbcTemplate.execute("DROP TABLE RACING_CAR IF EXISTS");
        jdbcTemplate.execute("DROP TABLE RACING_INFO IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE RACING_INFO("
            + "    id          INT UNSIGNED  NOT NULL AUTO_INCREMENT,"
            + "    winners     VARCHAR(50) NOT NULL,"
            + "    trial_count INT         NOT NULL,"
            + "    created_at  DATETIME    NOT NULL,"
            + "    PRIMARY KEY (id))");

        jdbcTemplate.execute("CREATE TABLE RACING_CAR("
            + "    id      INT         NOT NULL AUTO_INCREMENT,"
            + "    game_id INT         NOT NULL,"
            + "    name    VARCHAR(50) NOT NULL,"
            + "    move    INT         NOT NULL,"
            + "    PRIMARY KEY (id),"
            + "    FOREIGN KEY (game_id) REFERENCES RACING_INFO (id))");
    }

    @ParameterizedTest(name = "클라이언트가 잘못된 요청을 보낸 경우 400 에러 반환")
    @CsvSource(value = {"a, b, c:-3", "a:3", ":3", "a,a,b:3", "aaaaaaa,bb,cc:3"}, delimiter = ':')
    void badRequestTest(String names, int count) {
        final RacingCarRequestDto racingCarRequestDto = new RacingCarRequestDto(names, count);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingCarRequestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


    @DisplayName("클라이언트가 정상적인 요청을 보낸 경우 200 ok 반환")
    @Test
    void normalRequestTest() {
        final RacingCarRequestDto racingCarRequestDto = new RacingCarRequestDto("a,b,c", 3);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingCarRequestDto)
                .when().post("/plays")
                .then().log().all()
                .body("winners", anyOf(hasItem("a"), hasItem("b"), hasItem("c")))
                .body("racingCars", hasSize(3))
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("get 요청에 정상적으로 응답하는지 여부 테스트")
    @Test
    void getTest() {
        final RacingCarRequestDto racingCarRequestDto = new RacingCarRequestDto("a,b,c", 3);


        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(racingCarRequestDto)
            .when().post("/plays")
            .then().log().all();

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/plays")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .body("winners", hasSize(1))
            .body("racingCars", hasSize(1));
    }
}
