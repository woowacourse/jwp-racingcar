package racingcar.integration;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.RacingGameEntity;
import racingcar.dto.RacingGameRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GamePlayTest {

    @Autowired
    CarDao carDao;
    @Autowired
    RacingGameDao racingGameDao;

    @BeforeEach
    void setUp(@LocalServerPort int port) {
        RestAssured.port = port;
        Long firstId = racingGameDao.save(new RacingGameEntity(10));
        carDao.saveAll(List.of(new CarEntity("박스터", 10, true, firstId), new CarEntity("현구막", 5, false, firstId)));
        Long secondId = racingGameDao.save(new RacingGameEntity(5));
        carDao.saveAll(List.of(new CarEntity("벤츠", 3, false, secondId), new CarEntity("엔초", 4, true, secondId)));
    }

    @Test
    @DisplayName("자동차 경주 게임을 진행한다.")
    void playGame() {
        RacingGameRequest request = new RacingGameRequest(List.of("브리", "토미", "브라운"), 10);
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)

                .when().post("/plays")

                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .body("winners", notNullValue())
                .body("racingCars", hasSize(3));
    }

    @Test
    @DisplayName("자동차 경주 게임 이력을 조회한다")
    void findHistory() {
        RestAssured.given().log().all()

                .when().get("/plays")

                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(2));
    }
}
