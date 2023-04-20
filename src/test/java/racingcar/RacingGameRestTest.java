package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.web.RacingGameRequest;
import racingcar.web.RacingGameResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameRestTest {

    @Value("${local.server.port}")
    int port;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("delete from car_record");
        jdbcTemplate.execute("delete from racing_history");
    }

    @DisplayName("게임 실행 시, JSON으로 결과를 조회할 수 있다")
    @Test
    void playGame() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("바론,로지,져니", 10);
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequest)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", notNullValue())
                .body("racingCars", hasSize(3));
    }

    @DisplayName("게임 이력 조회 시 모든 결과를 리스트로 받을 수 있다.")
    @Test
    void readHistory() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("서브웨이, 로지", 10);
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequest)
                .when().post("/plays");
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameRequest)
                .when().post("/plays");
        //when
        Response response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays");
        //then
        response.then().statusCode(HttpStatus.OK.value());
        RacingGameResponse[] asResponseDto = response.as(RacingGameResponse[].class);
        assertThat(asResponseDto).hasSize(2);
    }

}
