package racingcar.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.any;

import io.restassured.RestAssured;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.GameRequest;
import racingcar.service.RacingCarsService;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RacingCarControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 게임_진행() {
        final GameRequest gameRequest = new GameRequest("브리,토미,브라운", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
                .when().post("/plays")
                .then().log().all()
                .body("winners", any(String.class))
                .body("racingCars", any(List.class))
                .body("racingCars[0].name", any(String.class))
                .body("racingCars[0].position", any(Integer.class))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void 잘못된_요청_데이터() {
        final GameRequest gameRequest = new GameRequest("  ", -1);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
                .when().post("/plays")
                .then().log().all()
                .body("message", any(Map.class))
                .body("message.names", is("공백은 입력할 수 없습니다. 입력 값 :   "))
                .body("message.count", is("1 미만의 값은 입력할 수 없습니다. 입력 값 : -1"))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Nested
    class findTotalHistoryTest {

        @Autowired
        private RacingCarsService racingCarsService;

        @BeforeEach
        void setUp() {
            racingCarsService.race(List.of("브리", "토미", "브라운"), 10);
        }

        @Test
        void 게임_이력_전체_조회() {
            RestAssured.given().log().all()
                    .when().get("/plays")
                    .then().log().all()
                    .body("history.size()", greaterThan(0))
                    .statusCode(HttpStatus.OK.value());
        }
    }
}
