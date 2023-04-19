package racingcar.jwp;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.service.dto.GameRequestDto;

@SpringBootTest
public class ApiControllerTest {

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
                .header("Location", "/plays");
    }
}
