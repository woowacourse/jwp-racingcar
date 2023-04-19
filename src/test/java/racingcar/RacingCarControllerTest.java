package racingcar;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@DisplayName("POST test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * HttpMethodController > createUser 메서드
     */
    @DisplayName("게임 정보를 입력하면 최종 결과를 반환한다.")
    @Test
    void play() {
        PlayRequest playRequest = new PlayRequest("브리,토미,브라운", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(playRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
