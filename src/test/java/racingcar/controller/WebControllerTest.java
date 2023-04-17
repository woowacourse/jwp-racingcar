package racingcar.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import racingcar.dto.GameDto;
import racingcar.model.Car;
import racingcar.repository.GameDao;
import racingcar.repository.RecordDao;

import static org.hamcrest.core.Is.is;

@TestPropertySource(locations = "/application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private GameDao gameDao;
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE;");
        jdbcTemplate.execute("TRUNCATE TABLE record RESTART IDENTITY;");
        jdbcTemplate.execute("TRUNCATE TABLE game RESTART IDENTITY;");
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE;");

        recordDao = new RecordDao(jdbcTemplate);

        gameDao.insert(10);
        recordDao.insert(1, false, new Car("a"));
        recordDao.insert(1, true, new Car("b"));

        gameDao.insert(20);
        recordDao.insert(2, false, new Car("a"));
        recordDao.insert(2, true, new Car("b"));
    }

    @DisplayName("게임 플레이 테스트")
    @Test
    void 게임_플레이_테스트() {
        GameDto gameDto = new GameDto("파워,도기", 10);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("size()", is(2));
    }

    @DisplayName("저장된 게임 반환 테스트")
    @Test
    void 저장된_게임_반환_테스트() {
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("size()", is(2));
    }
}
