package racingcar.controller;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import racingcar.controller.dto.GameRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RacingGameFixtures {

    @BeforeEach
    void setUp(@LocalServerPort final int port) {
        RestAssured.port = port;
    }

    public static ExtractableResponse<Response> savePlayers(String names, int trialCount) {
        final GameRequest request = new GameRequest(names, trialCount);

        return given()
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> findAllPlayers() {
        return given()
                .when().get("/plays")
                .then().log().all()
                .extract();
    }

    public static RequestSpecification given() {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
