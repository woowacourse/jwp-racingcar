package racingcar.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.service.dto.RacingStartDTO;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class CarRacingWebControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private CarRacingWebController carRacingWebController;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 게임을_생성_및_실행한다() {
        final RacingStartDTO racingStartDTO = new RacingStartDTO("huchu,gavi", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingStartDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", notNullValue())
                .body("racingCars", notNullValue());
    }

    @Test
    void 이력을_조회한다() {
        carRacingWebController.play(new RacingStartDTO("huchu,gavi", 1));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("[0].winners", Matchers.anyOf(Matchers.is("huchu"), Matchers.is("gavi"), Matchers.is("huchu,gavi")))
                .body("[0].racingCars", Matchers.any(List.class));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 이름이_null_혹은_빈값이면_예외가_발생한다(final String names) {
        final RacingStartDTO racingStartDTO = new RacingStartDTO(names, 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingStartDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("names", Is.is("이름을 입력해야 합니다."));
    }

    @ParameterizedTest
    @NullSource
    void 시도횟수가_null이면_예외가_발생한다(final Integer count) {
        final RacingStartDTO racingStartDTO = new RacingStartDTO("huchu,gavi", count);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingStartDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("count", Is.is("횟수를 입력해야 합니다."));
    }

    @Test
    void 시도횟수가_숫자가_아니면_예외가_발생한다() {
        final String invalidInput = "{\"names\": \"huchu,gavi\", \"count\": \"one\"}";

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(invalidInput)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(containsString("[ERROR] 시도할 횟수에 숫자를 입력할 수 없습니다. 입력 값 : one"));
    }

    @Test
    void 도메인_입력_요구사항을_만족하지_못하면_예외가_발생한다() {
        final RacingStartDTO racingStartDTO = new RacingStartDTO("huchu,huchu", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingStartDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(containsString("[ERROR] 자동차 이름은 중복될 수 없습니다."));
    }
}
