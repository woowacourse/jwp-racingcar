package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.AlwaysMoveNumberGenerator;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@Import(value = AlwaysMoveNumberGenerator.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RacingGameControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
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
}
