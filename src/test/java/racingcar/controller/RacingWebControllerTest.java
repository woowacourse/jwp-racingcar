package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingWebControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("정상 요청이 오면 상태코드 OK 반환")
    @Test
    void postRequestSuccess() {
        final TrackRequest trackRequest = new TrackRequest("gray,hoy,logan", "10");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(trackRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2));
    }

    @DisplayName("잘못된 이름 요청이 오면 상태코드 Bad Request 반환")
    @ParameterizedTest
    @CsvSource(value = {"gray.hoy.logan:자동차 이름은 문자와 숫자만 가능합니다.",
            "gra@,ho@,l@gan:자동차 이름은 문자와 숫자만 가능합니다.",
            "grayyy,hoy,logan:자동차 이름은 다섯 글자 이하여야 합니다.",
            "hoy,hoy,hoy:중복된 차 이름이 존재합니다."}, delimiter = ':')
    void postRequestFailWithWrongName(final String names, final String exceptionMessage) {
        final TrackRequest trackRequest = new TrackRequest(names, "10");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(trackRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", containsString(exceptionMessage));
    }

    @DisplayName("잘못된 시도 횟수 요청이 오면 상태코드 Bad Request 반환")
    @ParameterizedTest
    @CsvSource(value = {"two:시도 횟수는 숫자만 입력 가능합니다.",
            "0:시도 횟수는 1 이상 100 이하여야 합니다.",
            "101:시도 횟수는 1 이상 100 이하여야 합니다."}, delimiter = ':')
    void postRequestFailWithWrongTrialTimes(final String trialTime, final String exceptionMessage) {
        final TrackRequest trackRequest = new TrackRequest("gray,hoy,logan", trialTime);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(trackRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", containsString(exceptionMessage));
    }

    @DisplayName("게임 이력 조회 요청이 들어오면 상태코드 OK 반환")
    @Test
    void getRequestSuccess() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
