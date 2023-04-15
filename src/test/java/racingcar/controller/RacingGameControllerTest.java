package racingcar.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.GameInputDto;
import racingcar.service.RacingGameService;

import java.util.List;

import static org.hamcrest.Matchers.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql("/data.sql")
@SpringBootTest
@AutoConfigureMockMvc
class RacingGameControllerTest {
    @Autowired
    private RacingGameService racingGameService;
    @Autowired
    private MockMvc mockMvc;
    private GameInputDto gameInputDto;
    
    @BeforeEach
    void setUp() {
        gameInputDto = new GameInputDto("아벨,스플릿,포비", "12");
    }
    
    @Test
    void 이름과_시도횟수를_전달하면_게임_결과를_반환한다() {
        RestAssuredMockMvc.given().log().all()
                .mockMvc(mockMvc)
                .contentType(ContentType.JSON)
                .body(gameInputDto)
                .when().post("/plays")
                .then().log().all()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("winners", notNullValue())
                .body("racingCars", hasSize(3));
    }
    
    @Test
    void 진행했던_모든_게임의_결과를_반환한다() {
        racingGameService.playGameWithoutPrint(gameInputDto, () -> 5);
        racingGameService.playGameWithoutPrint(gameInputDto, () -> 5);
        
        RestAssuredMockMvc.given().log().all()
                .mockMvc(mockMvc)
                .when().get("/plays")
                .then().log().all()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("winners", is(List.of("아벨,스플릿,포비", "아벨,스플릿,포비")))
                .body("racingCars", hasSize(2));
    }
}