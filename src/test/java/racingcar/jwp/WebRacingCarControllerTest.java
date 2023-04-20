package racingcar.jwp;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.service.dto.GameRequestDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebRacingCarControllerTest {

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

    @Test
    @DisplayName("전체 게임 조회 테스트")
    void getAllTest() {
        // given
        final GameRequestDto gameRequestDto1 = new GameRequestDto("디투,레오", 10);
        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDto1)
                .post("/plays");

        final GameRequestDto gameRequestDto2 = new GameRequestDto("디투,에단,홍실,블랙캣", 10);
        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDto2)
                .post("/plays");

        // when
        ExtractableResponse<Response> response = RestAssured
                .given()
                .when().get("/plays")
                .then().log().all()
                .extract();
        Configuration conf = Configuration.defaultConfiguration();
        DocumentContext documentContext = JsonPath.using(conf).parse(response.asString());

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat((int) documentContext.read("$.size()")).isEqualTo(2),
                () -> assertThat((int) documentContext.read("$[0]['racingCars'].size()")).isEqualTo(2),
                () -> assertThat((int) documentContext.read("$[1]['racingCars'].size()")).isEqualTo(4),
                () -> assertThat((String) documentContext.read("$[0]['racingCars'][0]['name']")).isEqualTo("디투"),
                () -> assertThat((String) documentContext.read("$[0]['racingCars'][1]['name']")).isEqualTo("레오"),
                () -> assertThat((String) documentContext.read("$[1]['racingCars'][0]['name']")).isEqualTo("디투"),
                () -> assertThat((String) documentContext.read("$[1]['racingCars'][1]['name']")).isEqualTo("에단"),
                () -> assertThat((String) documentContext.read("$[1]['racingCars'][2]['name']")).isEqualTo("홍실"),
                () -> assertThat((String) documentContext.read("$[1]['racingCars'][3]['name']")).isEqualTo("블랙캣")
        );
    }

    @Test
    @DisplayName("유저 실수 관련 에러가 발생하면 BadRequest를 반환한다.")
    void userMistakeError() {
        final GameRequestDto gameRequestDto = new GameRequestDto("디투", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("예상치 못한 에러가 발생하면 InternalServerError를 반환한다.")
    void notExpectedError() {
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
