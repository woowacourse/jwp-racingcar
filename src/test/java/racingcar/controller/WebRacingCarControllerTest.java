package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.TableTruncator;
import racingcar.dto.NamesAndCountDto;

import static org.hamcrest.core.Is.is;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebRacingCarControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void afterEach() {
        TableTruncator.truncateTables(jdbcTemplate);
    }

    @Test
    void 이름과_시도_횟수를_요청했을_때() {
        final String names = "브리,토미,브라운";
        final int count = 10;
        final NamesAndCountDto namesAndCountDto = new NamesAndCountDto(names, count);

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(namesAndCountDto)
            .when().post("/plays")
            .then().log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    void 참가자_이름_제한_초과로_IllegalArgumentException이_발생했을_때() {
        final String names = "abcd, abcde";
        final int count = 10;
        final NamesAndCountDto namesAndCountDto = new NamesAndCountDto(names, count);

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(namesAndCountDto)
            .when().post("/plays")
            .then().log().all()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .body(is("[ERROR] 이름은 5글자까지 가능합니다."));
    }
}
