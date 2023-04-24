package racingcar.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.RacingGameRequest;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.service.RacingGameService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameWebControllerTest {

    @MockBean
    private RacingGameService racingGameService;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("사용자 이름과 횟수를 이용한 레이싱 게임 결과 반환한다.")
    @Test
    void playRacingGame() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("헤나,저문", 10);

        given(racingGameService.race(any(), anyInt())).willReturn(
                new RacingGameResponse("헤나", List.of(
                        new CarDto("헤나", 5), new CarDto("저문", 3)
                ))
        );

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", equalTo("헤나"))
                .rootPath("racingCars")
                .body("[0].name", equalTo("헤나"),
                        "[0].position", equalTo(5))
                .body("[1].name", equalTo("저문"),
                        "[1].position", equalTo(3));
    }

    @DisplayName("사용자 이름의 수가 올바르지 않을 때 400을 반환한다.")
    @Test
    void playRacingGameInvalidNumberOfName() {
        final RacingGameRequest request = new RacingGameRequest("저문", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("사용자 이름의 길이가 올바르지 않을 때 400을 반환한다.")
    @Test
    void playRacingGameInvalidLengthOfName() {
        final RacingGameRequest request = new RacingGameRequest("저문,내이름은헤나", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("전체 레이싱 결과 조회 요청을 확인한다.")
    @Test
    void findPlayingHistory() {
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("전체 레이싱 결과 조회의 반환된 값을 확인한다.")
    @Test
    void findPlayingHistoryReturns() {
        given(racingGameService.findHistory()).willReturn(List.of(
                new RacingGameResponse("저문", List.of(
                        new CarDto("저문", 7), new CarDto("헤나", 5)
                ))
        ));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("[0].winners", equalTo("저문"))
                .rootPath("[0].racingCars")
                .body("[0].name", equalTo("저문"),
                        "[0].position", equalTo(7))
                .body("[1].name", equalTo("헤나"),
                        "[1].position", equalTo(5));
    }
}
