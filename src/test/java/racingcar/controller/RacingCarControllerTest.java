package racingcar.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

import io.restassured.RestAssured;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("/plays POST 요청 테스트")
    @Test
    void play() throws JSONException {
        List<String> carNames = List.of("a", "b", "c");
        int count = 5;
        String requestJSON = requestJSON(carNames, count).toString();

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestJSON)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2))
                .body("racingCars.size()", is(carNames.size()));
    }

    @DisplayName("/plays POST 요청 예외 테스트 : 도메인 검증")
    @Test
    void playHandlingException() throws JSONException {
        List<String> carNames = List.of("123456", "a", "b");
        int count = 5;
        String requestJSON = requestJSON(carNames, count).toString();

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestJSON)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo("자동차 이름은 5자 이하여야 합니다."));
    }

    @DisplayName("/plays POST 요청 예외 테스트 : 컨트롤러 어노테이션 검증")
    @Test
    void playHandlingException2() throws JSONException {
        List<String> carNames = Collections.emptyList();
        int count = 5;
        String requestJSON = requestJSON(carNames, count).toString();

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestJSON)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo("플레이 요청 오류: 자동차 이름 목록은 빈 문자열일 수 없습니다."));
    }

    private JSONObject requestJSON(List<String> carNames, int count) throws JSONException {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("names", String.join(",", carNames));
        requestJSON.put("count", count);

        return requestJSON;
    }

    @DisplayName("/plays GET 요청 테스트")
    @Test
    void history() {
        RestAssured.given().log().all()
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
