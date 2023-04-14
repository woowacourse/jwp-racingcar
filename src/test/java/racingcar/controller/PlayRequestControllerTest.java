package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.model.PlayRequest;

import static org.hamcrest.core.Is.is;

@DisplayName("Http Method")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayRequestControllerTest {

    @BeforeEach
    void setUp(@LocalServerPort final int port) {
        RestAssured.port = port;
    }

    @DisplayName("Http Method - POST")
    @Test
    void playRacingCarGameTest() {
        final PlayRequest playRequest = new PlayRequest("echo,io", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(playRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("racingCars.size()", is(2));
    }

}
