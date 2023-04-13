package racingcar.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("doGame을 실행하면 게임의 결과가 반환된다.")
    @Test
    void doGame_givenCarNamesAndCount_thenReturnResult() {

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("names", "브리,토미,브라운");
        parameters.put("count", 10);

        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(parameters)
                .when().post("/plays")
                .then().log().all()
                .extract();

        final JsonPath result = response.jsonPath();

        assertAll(
                () -> assertThat(result.getString("winners")).isNotNull(),
                () -> assertThat(result.getList("racingCars.name", String.class)).containsExactly("브리", "토미", "브라운"),
                () -> assertThat(result.getList("racingCars.position", Integer.class)).hasSize(3)
        );
    }
}
