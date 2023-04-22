package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import racingcar.dto.PlayRequestDto;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RacingCarControllerTest {

    @Value("${server.port}")
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 게임_실행_요청에_이름이_없으면_요청은_실패한다() {
        final PlayRequestDto playRequestDto = new PlayRequestDto("", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(playRequestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 게임_실행_요청에_5글자가_넘는_이름이_있으면_실패한다() {
        final PlayRequestDto playRequestDto = new PlayRequestDto("123456,a,b", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(playRequestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 게임_실행_요청에_시도_횟수가_0이하면_에외가_발생한다(int tryCount) {
        //given
        final PlayRequestDto playRequestDto = new PlayRequestDto("123456,a,b", tryCount);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(playRequestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 정상_요청을_보낸다면_우승자와_유저들의_위치_정보를_보내준다() {
        final PlayRequestDto playRequestDto = new PlayRequestDto("a,b,c", 5);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(playRequestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(containsString("winners"))
                .body(containsString("racingCars"))
                .body("racingCars.size()", is(3));
    }

    @Test
    void 게임_기록_요청을_보내주면_이때까지의_게임_기록을_보내준다() {
        final RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:7080/plays";

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<String> stringHttpEntity = new HttpEntity<>("{\n\"names\" : \"a,b,c\",\n\"count\" : 3\n}", httpHeaders);

        for (int i = 0; i < 4; i++) {
            restTemplate.exchange(url, HttpMethod.POST, stringHttpEntity, String.class);
        }

        RestAssured.given().log().all()
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(4));
    }

}
