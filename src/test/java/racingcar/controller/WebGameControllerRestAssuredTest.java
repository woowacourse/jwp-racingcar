package racingcar.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.TestDatabaseConfig;
import racingcar.controller.dto.SinglePlayRequest;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@TestDatabaseConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebGameControllerRestAssuredTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("post(/plays) 테스트")
    void post_plays() {

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(ContentType.JSON)
                .body(new SinglePlayRequest("aa", 1))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", is("aa"))
                .body("racingCars.size()", is(1));

    }

    @Test
    @DisplayName("post(/plays) GameException bad request 테스트")
    void post_plays_GameException_bad_request() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new SinglePlayRequest("aa,aa", 1))

                .when()
                .post("/plays")

                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
