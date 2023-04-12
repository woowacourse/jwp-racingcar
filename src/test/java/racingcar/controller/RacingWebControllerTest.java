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
    void requestSuccess() {
        final TrackRequest trackRequest = new TrackRequest("gray,hoy,logan", "10");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(trackRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2));
    }


    @DisplayName("잘못된 이름 요청이 오면 상태코드 Bad Request 반환")
    @ParameterizedTest
    @CsvSource(value = {"gray.hoy.logan:InvalidCarNameFormatException",
            "gra@,ho@,l@gan:InvalidCarNameFormatException",
            "grayyy,hoy,logan:ExceedCarNameLengthException",
            "hoy,hoy,hoy:DuplicateCarNamesException"}, delimiter = ':')
    void requestFailWithWrongName(String names, String exceptionMessage) {
        final TrackRequest trackRequest = new TrackRequest(names, "10");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(trackRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is(exceptionMessage));
    }

    @DisplayName("잘못된 시도 횟수 요청이 오면 상태코드 Bad Request 반환")
    @ParameterizedTest
    @CsvSource(value = {"two:InvalidTrialTimesFormatException",
            "0:InvalidRangeTrialTimesException",
            "101:InvalidRangeTrialTimesException"}, delimiter = ':')
    void requestFailWithWrongTrialTimes(String trialTime, String exceptionMessage) {
        final TrackRequest trackRequest = new TrackRequest("gray,hoy,logan", trialTime);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(trackRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is(exceptionMessage));
    }
}
