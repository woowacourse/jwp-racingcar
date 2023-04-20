package racingcar.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import io.restassured.RestAssured;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.CarDto;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.service.RacingCarService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarWebControllerTest {

    @MockBean
    private RacingCarService racingCarService;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("POST 요청이 정상적으로 처리되었는지 확인한다.")
    void play() {
        GameRequest gameRequest = new GameRequest("조이,밀리", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("게임 실행 후 반환된 결과 값을 확인한다.")
    void requestBody_playGame() {
        GameRequest gameRequest = new GameRequest("조이,밀리", 5);

        given(racingCarService.play(any()))
                .willReturn(
                        GameResponse.of(
                                "조이,밀리",
                                List.of(CarDto.of("조이", 5), CarDto.of("밀리", 5))
                        )
                );

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", is("조이,밀리"))
                .rootPath("racingCars")
                .body("[0].name", equalTo("조이"), "[0].position", equalTo(5))
                .body("[1].name", equalTo("밀리"), "[1].position", equalTo(5));
    }

    @Test
    @DisplayName("GET 요청이 정상적으로 처리되었는지 확인한다.")
    void getGameResult() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("반환된 게임 목록을 확인한다.")
    void requestBody_gameResult() {
        given(racingCarService.getGameResults())
                .willReturn(
                        new ArrayList<>() {{
                            add(GameResponse.of("밀리",
                                    List.of(CarDto.of("조이", 4), CarDto.of("밀리", 5)))
                            );
                        }}
                );

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("[0].winners", equalTo("밀리"))
                .body("[0].racingCars[0].name", equalTo("조이"),
                        "[0].racingCars[0].position", equalTo(4))
                .body("[0].racingCars[1].name", equalTo("밀리"),
                        "[0].racingCars[1].position", equalTo(5));
    }
}
