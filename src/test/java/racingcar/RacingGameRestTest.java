package racingcar;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.RacingGameRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameRestTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("게임 실행 시, JSON으로 결과를 조회할 수 있다")
    @Test
    void playGame() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("바론,로지,져니", 10);
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", notNullValue())
                .body("racingCars", hasSize(3));
    }

    @DisplayName("게임 이력을 조회할 수 있다")
    @Test
    void viewAllGameHistories() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("바론,로지,져니", 10);
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequest)
                .post("/plays");

        RestAssured.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(1));
    }

    @DisplayName("잘못된 이동 횟수를 입력할 경우 400에러가 반환된다.")
    @Test
    void wrongTrialCountBadRequest() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("바론,로지,져니", -10);
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("중복 이름이면 400에러가 반환된다.")
    @Test
    void duplicateNameBadRequest() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("바론,바론", 10);
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("빈 값을 입력하면 400에러가 반환된다.")
    @Test
    void emptyNameBadRequest() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("", 10);
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
