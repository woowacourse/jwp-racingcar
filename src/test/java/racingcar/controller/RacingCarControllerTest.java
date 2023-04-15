package racingcar.controller;

import static org.hamcrest.core.Is.is;

import io.restassured.RestAssured;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void play() throws JSONException {
        List<String> carNames = List.of("a", "b", "c");
        int count = 5;
        String requestJSON = requestJSON(carNames, count).toString();

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestJSON)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2))
                .body("racingCars.size()", is(carNames.size()));
    }

    private JSONObject requestJSON(List<String> carNames, int count) throws JSONException {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("names", String.join(",", carNames));
        requestJSON.put("count", count);

        return requestJSON;
    }

    @Test
    void history() {
        RestAssured.given().log().all()
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
