package racingcar.jwp;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.service.dto.GameRequestDto;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @Value("${local.server.port}")
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("게임 저장 테스트")
    void postTest() {
        final GameRequestDto gameRequestDto = new GameRequestDto("ditoo,leo", 10);

        RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", "/plays")
                .body("winners", notNullValue())
                .body("racingCars.size()", is(2))
                .body("racingCars[0].name", is("ditoo"))
                .body("racingCars[1].name", is("leo"));
    }
}
