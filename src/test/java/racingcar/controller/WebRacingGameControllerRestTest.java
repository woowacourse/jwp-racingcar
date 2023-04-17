package racingcar.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import racingcar.dto.GameInputDto;
import racingcar.service.RacingGameServiceImpl;

import static org.hamcrest.Matchers.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql("/init.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebRacingGameControllerRestTest {
    @LocalServerPort
    private int port;
    @Autowired
    private RacingGameServiceImpl racingGameServiceImpl;
    
    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
    
    @Test
    void 이름과_시도횟수를_전달하면_게임_결과를_반환한다() {
        GameInputDto gameInputDto = new GameInputDto("아벨,스플릿,포비", "12");
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(gameInputDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("winners", notNullValue())
                .body("racingCars.name", contains("아벨", "스플릿", "포비"));
    }
    
    @Test
    void 진행했던_모든_게임의_결과를_반환한다() {
        racingGameServiceImpl.playGameWithoutPrint(new GameInputDto("아벨,스플릿,포비", "12"), () -> 5);
        racingGameServiceImpl.playGameWithoutPrint(new GameInputDto("aa,bb,cc", "12"), () -> 5);
        
        RestAssured.given().log().all()
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("size()", is(2))
                .body("[0].winners", is("아벨,스플릿,포비"))
                .body("[1].winners", is("aa,bb,cc"))
                .body("[0].racingCars.name", contains("아벨", "스플릿", "포비"))
                .body("[1].racingCars.name", contains("aa", "bb", "cc"));
    }
    
    @Test
    void 이름_길이_범위_초과_시_예외_발생() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameInputDto("splited", "12"))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", is("부적절한 이름입니다."));
    }
    
    @ParameterizedTest(name = "name = {0}")
    @ValueSource(strings = {"spli-", "spl-t", "-splt"})
    void 들어갈_수_없는_문자_존재할_시_예외_발생(String names) {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameInputDto(names, "12"))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", is("부적절한 이름입니다."));
    }
    
    @ParameterizedTest(name = "{displayName} : name = {0}")
    @NullAndEmptySource
    void names_null_또는_empty_입력_시_예외_발생(String names) {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameInputDto(names, "12"))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", is("이름을 입력해주세요"));
    }
    
    @ParameterizedTest(name = "{displayName} : name = {0}")
    @NullAndEmptySource
    void count_null_또는_empty_입력_시_예외_발생(String count) {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameInputDto("abel", count))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", is("시도 횟수를 입력해주세요"));
    }
    
    @ParameterizedTest(name = "{displayName} : name = {0}")
    @NullAndEmptySource
    void names_and_count_null_또는_empty_입력_시_예외_발생(String param) {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameInputDto(param, param))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", containsString("이름을 입력해주세요"))
                .body("message", containsString(System.lineSeparator()))
                .body("message", containsString("시도 횟수를 입력해주세요"));
    }
}
