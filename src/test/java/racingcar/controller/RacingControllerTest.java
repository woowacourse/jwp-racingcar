package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

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
        @DisplayName("GET 응답의 상태코드는 405이다")
        void shouldResponse405WhenGetRequest() {
            RestAssured.given().log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when().get("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
        }

        @Test
        @DisplayName("필요한 정보를 모두 담아 요청했을 때의 상태 코드는 200이다")
        void shouldResponseWhenPostRequest() {
            Model model = new ConcurrentModel();
            model.addAttribute("names", "브리,토미,브라운");
            model.addAttribute("count", "3");
            RestAssured.given().log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(model)
                    .when().post("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.OK.value());
        }

        @Test
        @DisplayName("플레이어들의 이름만 전송했을 때에는 400 예외를 발생한다")
        void shouldResponse400WhenRequestWithOnlyPlayerNames() {
            Model model = new ConcurrentModel();
            model.addAttribute("names", "브리,토미,브라운");
            RestAssured.given().log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
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
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(model)
                    .when().post("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = " ")
        @DisplayName("플레이어 이름이 빈 문자열이라면 400 예외를 발생한다")
        void shouldResponse400WhenRequestWithBlankPlayerNames(final String inputNames) {
            Model model = new ConcurrentModel();
            model.addAttribute("names", inputNames);
            model.addAttribute("count", "3");
            RestAssured.given().log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(model)
                    .when().post("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "-1", "-5"})
        @DisplayName("시도 횟수가 0 이하이면 예외를 발생한다")
        void shouldResponse400WhenRequestWithCountBelowZero(final String inputCount) {
            Model model = new ConcurrentModel();
            model.addAttribute("names", "브리,토미,브라운");
            model.addAttribute("count", inputCount);
            RestAssured.given().log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(model)
                    .when().post("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("올바른 요청 시, 우승자와 자동차 정보를 반환한다")
        void shouldReturnWinnersAndRacingCarsWhenRequestCorrectly() {
            Model model = new ConcurrentModel();
            model.addAttribute("names", "브리,토미,브라운");
            model.addAttribute("count", "3");
            RestAssured.given().log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(model)
                    .when().post("/plays")
                    .then().log().all()
                    .statusCode(HttpStatus.OK.value())
                    .body("winners", notNullValue())
                    .body("racingCars", notNullValue())
                    .body("racingCars.size()", is(3))
                    .body("racingCars[0].name", notNullValue())
                    .body("racingCars[0].position", notNullValue())
                    .body("racingCars[1].name", notNullValue())
                    .body("racingCars[1].position", notNullValue())
                    .body("racingCars[2].name", notNullValue())
                    .body("racingCars[2].position", notNullValue());
        }
    }

    @Test
    @DisplayName("정의되지 않은 경로에 대한 POST 응답의 상태코드는 404이다")
    void shouldResponse404WhenPostRequestToUndefinedPath() {
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/undefined")
                .then().log().all()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
