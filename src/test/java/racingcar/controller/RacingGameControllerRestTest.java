package racingcar.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import racingcar.dto.GameInputDto;
import racingcar.service.RacingGameService;

import java.util.List;

import static org.hamcrest.Matchers.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql("/init.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameControllerRestTest {
    @LocalServerPort
    private int port;
    @Autowired
    private RacingGameService racingGameService;
    
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
                .body("racingCars", hasSize(3));
    }
    
    @Test
    void 진행했던_모든_게임의_결과를_반환한다() {
        racingGameService.playGameWithoutPrint(new GameInputDto("아벨,스플릿,포비", "12"), () -> 5);
        racingGameService.playGameWithoutPrint(new GameInputDto("aa,bb,cc", "12"), () -> 5);
        
        RestAssured.given().log().all()
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("winners", is(List.of("아벨,스플릿,포비", "aa,bb,cc")))
                .body("racingCars", hasSize(2));
    }
}
