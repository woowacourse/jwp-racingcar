package racingcar.controller;

import static org.hamcrest.core.IsInstanceOf.any;

import io.restassured.RestAssured;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import racingcar.dto.GameRequest;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
class RacingCarControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 게임_진행() {
        final GameRequest gameRequest = new GameRequest(List.of("브리", "토미", "브라운"), 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
                .when().post("/plays")
                .then().log().all()
                .body("winners", any(String.class))
                .body("racingCars", any(List.class))
                .body("racingCars[0].name", any(String.class))
                .body("racingCars[0].position", any(Integer.class))
                .statusCode(HttpStatus.OK.value());
    }
}
