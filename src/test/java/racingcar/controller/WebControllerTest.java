package racingcar.controller;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.RacingGameRequestDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("playGame - POST")
    void playGame() {
        RacingGameRequestDto racingGameRequestDto = new RacingGameRequestDto("ocean,mint", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2));
    }

    @Test
    @DisplayName("getGameRecord - Get")
    void getGameRecord() {
        playGame();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(1)).extract();

        assertThat(response.jsonPath().getString("racingCars")).contains("ocean", "mint");
    }
}

