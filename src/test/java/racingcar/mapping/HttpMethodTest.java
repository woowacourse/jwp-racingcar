package racingcar.mapping;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.domain.Car;
import racingcar.dto.GameInputDto;

@DisplayName("Http Method")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpMethodTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
    @DisplayName("Http Method - POST")
    @Test
    void createUser() {
        Car car = new Car("test");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(car)
                .when().post("/http-method/car")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", "/car/1");
    }

    @DisplayName("이름과 실행 횟수 POST")
    @Test
    void postInput() {
        GameInputDto gameInputDto = new GameInputDto("브리,토미,브라운", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameInputDto)
                .when().post("/http-method/racingGame")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", "/racingGame/1");
    }
}
