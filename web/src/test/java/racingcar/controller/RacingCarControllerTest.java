package racingcar.controller;

import static org.hamcrest.core.IsInstanceOf.any;

import io.restassured.RestAssured;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import racingcar.dto.GameRequest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
class RacingCarControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("게임을 진행하고, 결과를 조회한다")
    @TestFactory
    Stream<DynamicTest> dynamicTestFromCollections() {
        return Stream.of(
                DynamicTest.dynamicTest("게임을 진행한다", () -> {
                    final GameRequest gameRequest = new GameRequest(List.of("브리", "토미", "브라운"), 10);

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
                }),
                DynamicTest.dynamicTest("게임 결과를 조회한다", () -> RestAssured.given().log().all()
                        .when().get("/plays")
                        .then().log().all()
                        .body("[0].winners", any(String.class))
                        .body("[0].racingCars", any(List.class))
                        .body("[0].racingCars[0].name", any(String.class))
                        .body("[0].racingCars[0].position", any(Integer.class))
                        .statusCode(HttpStatus.OK.value()))
        );
    }
}
