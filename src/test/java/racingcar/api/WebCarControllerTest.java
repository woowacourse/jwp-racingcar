package racingcar.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import racingcar.dto.GameDto;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebCarControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void playGame() {
        playGame(new GameDto("무민,준팍", "10"))
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("winners", notNullValue())
                .body("racingCars", hasSize(2));
    }

    @Test
    void getPlayHistories() {
        final GameDto gameDto = new GameDto("무민,준팍", "10");

        playGame(gameDto);
        playGame(gameDto);

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .get("/plays")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("winners", hasSize(2))
                    .body("racingCars", hasSize(2));
    }

    private Response playGame(final GameDto gameDto) {
        return RestAssured
                .given()
                    .body(gameDto)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/plays");
    }
}