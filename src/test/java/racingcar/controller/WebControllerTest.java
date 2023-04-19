package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.repository.GameDao;
import racingcar.repository.RecordDao;
import racingcar.request.PlayRequest;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GameDao gameDao;
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeEach() {
        String sql = "DROP TABLE IF EXISTS record;\n" +
                "DROP TABLE IF EXISTS game;\n" +
                "\n" +
                "CREATE TABLE game\n" +
                "(\n" +
                "    id          int PRIMARY KEY AUTO_INCREMENT      NOT NULL,\n" +
                "    trial_count int                                 NOT NULL,\n" +
                "    game_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE record\n" +
                "(\n" +
                "    game_id     int         NOT NULL,\n" +
                "    position    int         NOT NULL,\n" +
                "    is_winner   boolean     NOT NULL,\n" +
                "    player_name varchar(30) NOT NULL,\n" +
                "    PRIMARY KEY (game_id, player_name),\n" +
                "    FOREIGN KEY (game_id) REFERENCES game (id)\n" +
                ");\n";
        jdbcTemplate.execute(sql);
    }

    @Test
    @DisplayName("POST /plays 테스트")
    void POST_plays_테스트() throws Exception {
        // given
        PlayRequest playRequest = new PlayRequest("1,2,3", 10);
        String request = objectMapper.writeValueAsString(playRequest);

        // expect
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").isNotEmpty())
                .andExpect(jsonPath("$.racingCars", hasSize(3)))
                .andDo(print());

        assertThat(recordDao.findAllByGameId(1).size()).isEqualTo(3);
    }

    @Test
    @DisplayName("GET /plays 테스트")
    void GET_plays_테스트() throws Exception {
        // given
        long gameId = gameDao.insert(10);
        assertThat(gameDao.countAll()).isEqualTo(1);

        recordDao.insert(gameId, false, new Car("도기"));
        recordDao.insert(gameId, true, new Car("허브"));
        assertThat(recordDao.findAllByGameId(gameId).size()).isEqualTo(2);

        // expect
        mockMvc.perform(get("/plays"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].winners").value("허브"))
                .andExpect(jsonPath("$[0].racingCars[0].name").value("도기"))
                .andExpect(jsonPath("$[0].racingCars[1].name").value("허브"))
                .andDo(print());
    }
}
