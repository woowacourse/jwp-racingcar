package racingcar.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.config.TestConfig;
import racingcar.dto.GameRequest;
import racingcar.strategy.MovingStrategy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({TestConfig.class})
class RacingGameWebControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    MovingStrategy movingStrategy;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("POST 요청이 정상적으로 처리되었는지 확인하고, 반환 값을 확인한다.")
    void createGame() {
        GameRequest gameRequest = new GameRequest("조이,밀리", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", is("조이,밀리"))
                .rootPath("racingCars")
                .body("[0].name", equalTo("조이"), "[0].position", equalTo(5))
                .body("[1].name", equalTo("밀리"), "[1].position", equalTo(5));
    }
}
