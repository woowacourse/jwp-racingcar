package racingcar.integration;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
public class RacingGameControllerIntTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 게임을_실행한다() {
        // when
        final ExtractableResponse<Response> 게임_결과 = 게임_실행_요청("브리", "토미", "브라운");

        // then
        assertAll(
                () -> assertThat(N_번째_자동차의_이름(0, 게임_결과)).isEqualTo("브리"),
                () -> assertThat(N_번째_자동차의_이름(1, 게임_결과)).isEqualTo("토미"),
                () -> assertThat(N_번째_자동차의_이름(2, 게임_결과)).isEqualTo("브라운"),
                () -> assertThat(게임_결과.statusCode()).isEqualTo(HttpStatus.OK.value())
        );
    }

    @Test
    void 게임_목록을_조회한다() {
        // given
        게임_실행_요청("브리", "토미", "브라운");
        게임_실행_요청("브리", "토미", "브라운");

        // when
        ExtractableResponse<Response> 게임_결과_목록 = 게임_조회_요청();

        // then
        assertAll(
                () -> assertThat(전체_게임의_수(게임_결과_목록)).isEqualTo(2),
                () -> assertThat(N_번째_게임의_자동차의_수(0, 게임_결과_목록)).isEqualTo(3),
                () -> assertThat(게임_결과_목록.statusCode()).isEqualTo(HttpStatus.OK.value())
        );
    }

    private ExtractableResponse<Response> 게임_실행_요청(final String... names) {
        final String body = "{\"names\": \"" + String.join(",", names) + "\", \"count\": 10}";
        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .body(body)
                .when().post("/plays")
                .then().log().all().extract();
    }

    private ExtractableResponse<Response> 게임_조회_요청() {
        return RestAssured
                .given().log().all()
                .when().get("/plays")
                .then().log().all()
                .body("$.size()", equalTo(2))
                .body("[0].racingCars.size()", equalTo(3))
                .extract();
    }

    private String N_번째_자동차의_이름(final int index, final ExtractableResponse<Response> 게임_결과) {
        return 게임_결과.jsonPath().getString("racingCars[" + index + "].name");
    }

    private int N_번째_게임의_자동차의_수(final int index, final ExtractableResponse<Response> 게임_결과_목록) {
        return 게임_결과_목록.jsonPath().getInt("[" + index + "].racingCars.size()");
    }

    private int 전체_게임의_수(final ExtractableResponse<Response> 게임_결과_목록) {
        return 게임_결과_목록.jsonPath().getInt("$.size()");
    }
}
