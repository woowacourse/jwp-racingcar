package racingcar.controller;

import io.restassured.RestAssured;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest
class RacingControllerTest {

    @Nested
    @DisplayName("/plays :POST")
    class PlaysPost {

        @Test
        @DisplayName("POST 응답의 상태코드는 200이다")
        void shouldResponseWhenPostRequest() {
            RestAssured.given().log().all()
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .when().post("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.OK.value());
        }

        @Test
        @DisplayName("GET 응답의 상태코드는 405이다")
        void shouldResponse405WhenGetRequest() {
            RestAssured.given().log().all()
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .when().get("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
        }
    }

    @Test
    @DisplayName("정의되지 않은 경로에 대한 POST 응답의 상태코드는 404이다")
    void shouldResponse404WhenPostRequestToUndefinedPath() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/undefined")
                .then().log().all()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
