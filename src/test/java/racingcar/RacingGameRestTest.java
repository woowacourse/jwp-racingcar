package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingCarRecord;
import racingcar.dao.RacingCarRecordDao;
import racingcar.dao.RacingGameHistoryDao;
import racingcar.domain.cars.RacingCar;
import racingcar.web.RacingGameRequest;
import racingcar.web.RacingGameResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RacingGameRestTest {

    @Value("${local.server.port}")
    int port;

    @Autowired
    private RacingCarRecordDao racingCarRecordDao;

    @Autowired
    private RacingGameHistoryDao racingGameHistoryDao;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Order(1)
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

    @Order(2)
    @DisplayName("게임 이력 조회 시 모든 결과를 리스트로 받을 수 있다.")
    @Test
    void readHistory() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("서브웨이, 로지", 10);
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
        assertThat(asResponseDto[0].getRacingCars()).hasSize(3);
        assertThat(asResponseDto[1].getRacingCars()).hasSize(2);
    }

}
