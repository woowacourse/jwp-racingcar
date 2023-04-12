package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Nested
    @DisplayName("/plays :POST")
    class PlaysPost {

        @Test
        @DisplayName("POST 응답의 상태코드는 200이다")
        void shouldResponseWhenPostRequest() {
            RestAssured.given().log().all()
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .body(new ConcurrentModel())
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

        @Test
        @DisplayName("플레이어들의 이름만 전송했을 때에는 400 예외를 발생한다")
        void shouldResponse400WhenRequestWithOnlyPlayerNames() {
            Model model = new ConcurrentModel();
            model.addAttribute("names", "브리,토미,브라운");
            RestAssured.given().log().all()
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .body(model)
                    .when().post("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("시도 횟수만 전송했을 때에는 400 예외를 발생한다")
        void shouldResponse400WhenRequestWithOnlyTryCount() {
            Model model = new ConcurrentModel();
            model.addAttribute("count", "3");
            RestAssured.given().log().all()
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .body(model)
                    .when().post("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
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
