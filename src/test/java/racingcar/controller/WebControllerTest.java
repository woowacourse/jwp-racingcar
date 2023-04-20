package racingcar.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.RacingStartDTO;

import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class WebControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private WebController webController;

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
        webController.createGameAndPlay(new RacingStartDTO("huchu,gavi", 1));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("[0].winners", Matchers.anyOf(Matchers.is("huchu"), Matchers.is("gavi"), Matchers.is("huchu,gavi")))
                .body("[0].racingCars", Matchers.any(List.class));
    }
}
