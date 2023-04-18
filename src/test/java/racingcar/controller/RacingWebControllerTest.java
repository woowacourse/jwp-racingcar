package racingcar.controller;

import static org.hamcrest.core.Is.is;

import io.restassured.RestAssured;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.PlayRequestDto;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RacingWebControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 플레이어_1명인_경우_테스트() {
        PlayRequestDto requestDto = new PlayRequestDto("오잉", 2);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", is("오잉"))
                .body("racingCars[0].name", is("오잉"));
    }

    @Test
    void 플레이어_여러명인_경우_테스트() {
        PlayRequestDto requestDto = new PlayRequestDto("오잉,포이,브리", 3);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("racingCars[0].name", is("오잉"))
                .body("racingCars[1].name", is("포이"))
                .body("racingCars[2].name", is("브리"));
    }
}
