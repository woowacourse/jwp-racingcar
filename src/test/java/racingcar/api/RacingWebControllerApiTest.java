package racingcar.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import racingcar.controller.dto.TrackRequest;

import static org.hamcrest.Matchers.is;

@Sql("/truncate.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RacingWebControllerApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("참여자와 시도횟수를 입력하면 승리자와 모든 참여자 정보를 가진 2개의 데이터를 반환")
    @Test
    void postPlaySuccess() {
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

    @DisplayName("잘못된 이름 요청이 들어오면 상태 코드 Bad Request와 에러 메시지를 반환")
    @ParameterizedTest
    @CsvSource(value = {"gray.hoy.logan:자동차 이름은 문자와 숫자만 가능합니다.",
            "gra@,ho@,l@gan:자동차 이름은 문자와 숫자만 가능합니다.",
            "grayyy,hoy,logan:자동차 이름은 다섯 글자 이하여야 합니다.",
            "hoy,hoy,hoy:중복된 차 이름이 존재합니다.",
            ":참여자 이름을 입력해야합니다.",
            " , :비어있는 자동차 이름이 존재합니다."}, delimiter = ':')
    void postPlayFailWithWrongName(final String names, final String exceptionMessage) {
        final TrackRequest trackRequest = new TrackRequest(names, "10");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(trackRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", is(exceptionMessage));
    }

    @DisplayName("잘못된 시도 횟수 요청이 들어오면 상태코드 Bad Request와 에러 메시지를 반환")
    @ParameterizedTest
    @CsvSource(value = {"two:시도 횟수는 숫자만 입력 가능합니다.",
            "0:시도 횟수는 1 이상 100 이하여야 합니다.",
            "101:시도 횟수는 1 이상 100 이하여야 합니다."}, delimiter = ':')
    void postPlayFailWithWrongTrialTimes(final String trialTime, final String exceptionMessage) {
        final TrackRequest trackRequest = new TrackRequest("gray,hoy,logan", trialTime);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(trackRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", is(exceptionMessage));
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
