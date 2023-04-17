package racingcar.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import racingcar.controller.dto.RacingGameRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameControllerTest {
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
                .contentType(APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(OK.value())
                .body(Matchers.stringContainsInOrder("winners", "racingCars", "name", "position"));
    }

    @DisplayName("레이싱 게임에 참여할 사용자 이름이 한 명 이하일 경우 BAD_REQUEST(400)을 응답한다.")
    @Test
    void throwExceptionWhenNamesIsOnePerson() {
        final RacingGameRequest request = new RacingGameRequest("헤나", 10);

        RestAssured.given().log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(BAD_REQUEST.value());
    }
}
