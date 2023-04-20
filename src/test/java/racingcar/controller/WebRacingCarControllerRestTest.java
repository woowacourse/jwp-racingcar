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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameRequestDto;

import static org.hamcrest.Matchers.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class WebRacingCarControllerRestTest {
    @LocalServerPort
    private int port;
    
    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
    
    @Test
    void 이름과_시도횟수를_전달하면_게임_결과를_반환한다() {
        final GameRequestDto gameRequestDto = new GameRequestDto("아벨,스플릿,포비", "20");
        
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(gameRequestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("winners", notNullValue())
                .body("racingCars.name", contains("아벨", "스플릿", "포비"));
    }
    
    @Test
    void 이름_길이_범위_초과_시_예외_발생() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameRequestDto("splited", "12"))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", is("이름의 길이를 벗어났습니다. 다시 입력해주세요. 입력된 name : splited"));
    }
    
    @ParameterizedTest(name = "name = {0}")
    @ValueSource(strings = {"spli-", "spl-t", "-splt"})
    void 들어갈_수_없는_문자_존재할_시_예외_발생(String names) {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameRequestDto(names, "12"))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", is("[ERROR] 차 이름은 쉼표로 구분해서 영어와 한글로만 입력할 수 있습니다. 다시 입력해주세요."));
    }
    
    @ParameterizedTest(name = "{displayName} : name = {0}")
    @NullAndEmptySource
    void names_null_또는_empty_입력_시_예외_발생(String names) {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameRequestDto(names, "12"))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", is("[ERROR] 이름을 입력해주세요."));
    }
    
    @ParameterizedTest(name = "{displayName} : name = {0}")
    @NullAndEmptySource
    void count_null_또는_empty_입력_시_예외_발생(String count) {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameRequestDto("abel", count))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", is("[ERROR] 시도 횟수를 입력해주세요."));
    }
    
    @ParameterizedTest(name = "{displayName} : name = {0}")
    @NullAndEmptySource
    void names_and_count_null_또는_empty_입력_시_예외_발생(String param) {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new GameRequestDto(param, param))
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("message", containsString("[ERROR] 이름을 입력해주세요."))
                .body("message", containsString(System.lineSeparator()))
                .body("message", containsString("[ERROR] 시도 횟수를 입력해주세요."));
    }
}
