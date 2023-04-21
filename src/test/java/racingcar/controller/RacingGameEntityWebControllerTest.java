package racingcar.controller;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.PostGameRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class RacingGameEntityWebControllerTest {

    @BeforeEach
    void setUp(@LocalServerPort int port) {
        RestAssured.port = port;
    }

    @DisplayName("doGame을 실행하면 게임의 결과가 반환된다.")
    @Test
    void doGame_givenCarNamesAndCount_thenReturnResult() {

        String names = "브리,토미,브라운";
        int count = 10;
        PostGameRequest postGameRequest = new PostGameRequest(names, count);

        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(postGameRequest)
                .when().post("/plays")
                .then().log().all()
                .extract();

        final JsonPath result = response.jsonPath();

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(result.getString("winners")).isNotNull(),
                () -> assertThat(result.getList("racingCars.name", String.class)).containsExactly("브리", "토미", "브라운"),
                () -> assertThat(result.getList("racingCars.position", Integer.class)).hasSize(3)
        );
    }

    @DisplayName("findGame을 실행하면 게임의 이력이 반환된다.")
    @Test
    void findGame_WhenCall_thenReturnResult() {
        String names = "브리,토미,브라운";
        int count = 10;
        PostGameRequest postGameRequest = new PostGameRequest(names, count);

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(postGameRequest)
                .when().post("/plays");

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(postGameRequest)
                .when().post("/plays");


        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/plays")
                .then().log().all()
                .extract();

        final JsonPath result = response.jsonPath();

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        );
    }
}
