package racingcar.controller;

import static org.hamcrest.core.IsNull.notNullValue;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.controller.dto.GameInfoRequest;

@SpringBootTest
class RacingCarWebControllerTest {

    @Test
    @DisplayName("자동차 이름, 시도 횟수가 담긴 요청 바디를 받아서 게임 결과를 응답 바디에 반환한다.")
    void registerRaceResult() {
        GameInfoRequest gameInfoRequest = new GameInfoRequest("성하,이오,코코닥", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameInfoRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", notNullValue())
                .body("racingCars", notNullValue());
    }
}
