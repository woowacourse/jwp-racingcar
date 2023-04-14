package racingcar.integration;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.RacingGameRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GamePlayTest {

    @BeforeEach
    void setUp(@LocalServerPort int port) {
        RestAssured.port = port;
    }

    @Test
    void playGame() {
        RacingGameRequest request = new RacingGameRequest("브리,토미,브라운", 10);
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)

                .when().post("/plays")

                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", notNullValue())
                .body("racingCars", hasSize(3));
    }
}
