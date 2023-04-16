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
import racingcar.dto.GameDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        jdbcTemplate.execute("DROP TABLE record IF EXISTS");
        jdbcTemplate.execute("DROP TABLE game IF EXISTS");

        jdbcTemplate.execute("CREATE TABLE game (\n" +
                "    id int PRIMARY KEY AUTO_INCREMENT,\n" +
                "    trial_count int NOT NULL,\n" +
                "    game_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                ");");

        jdbcTemplate.execute("DROP TABLE record IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE record\n" +
                "(\n" +
                "    player_name varchar(30),\n" +
                "    game_id     int,\n" +
                "    position    int     NOT NULL,\n" +
                "    is_winner   boolean NOT NULL,\n" +
                "    PRIMARY KEY (player_name, game_id),\n" +
                "    FOREIGN KEY (game_id) REFERENCES game (id)\n" +
                ");");

        List<Object[]> trial_count = Arrays.asList(new String[]{"10"}, new String[]{"20"});
        jdbcTemplate.batchUpdate("INSERT INTO game(trial_count) VALUES (?)", trial_count);

        List<Object[]> records = Stream.of(new Object[]{1, 8, false, "doggy"}, new Object[]{2, 7, true, "power"})
                .collect(Collectors.toList());
        jdbcTemplate.batchUpdate("INSERT INTO record(game_id, position, is_winner, player_name) VALUES (?, ?, ?, ?)", records);
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
