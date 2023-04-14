package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.RacingGameRequestDto;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarWebControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("이름과 시도 횟수를 보내면 결과로 우승자와 참가자(이름,포지션)을 반환한다")
    @Test
    void return_result_given_names_and_trial_count() {
        RacingGameRequestDto racingGameRequestDto = new RacingGameRequestDto("마코,아코", 10);

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(racingGameRequestDto)
            .when().post("/plays")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("size()", is(2))
            .body("racingCars.name", hasItems("아코", "마코"));
    }
}
