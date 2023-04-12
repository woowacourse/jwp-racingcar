package racingcar.controller;

import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.AlwaysMoveNumberGenerator;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;
import racingcar.service.RacingGameService;

@Import(value = AlwaysMoveNumberGenerator.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RacingGameControllerTest {

    @Autowired
    RacingGameService racingGameService;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void playGame() {
        RacingGameRequest request = new RacingGameRequest("브리,토미,브라운", 10);
        RacingGameResponse response = racingGameService.play(request);
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", is(response.getWinners()));
    }

}
