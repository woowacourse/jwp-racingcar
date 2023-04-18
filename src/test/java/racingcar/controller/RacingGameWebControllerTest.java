package racingcar.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import racingcar.controller.dto.RacingGameRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameWebControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("사용자 이름과 횟수를 이용한 레이싱 게임 결과 반환 스토리")
    @Test
    void playRacingGame() {
        final RacingGameRequest request = new RacingGameRequest("저문,헤나", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(Matchers.stringContainsInOrder("winners", "racingCars", "name", "position"));
    }

    @DisplayName("사용자 이름의 수가 올바르지 않을 때 400반환")
    @Test
    void playRacingGameInvalidNumberOfName() {
        final RacingGameRequest request = new RacingGameRequest("저문", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("사용자 이름의 길이가 올바르지 않을 때 400반환")
    @Test
    void playRacingGameInvalidLengthOfName() {
        final RacingGameRequest request = new RacingGameRequest("저문,내이름은헤나", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("전체 레이싱 결과 조회")
    @Test
    void findPlayingHistory() {
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
