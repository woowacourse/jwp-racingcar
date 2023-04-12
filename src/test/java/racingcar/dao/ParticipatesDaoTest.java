package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.ParticipateDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ParticipatesDaoTest {

    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final ParticipatesDao participatesDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParticipatesDaoTest(final GameDao gameDao, final PlayerDao playerDao, final ParticipatesDao participatesDao, final JdbcTemplate jdbcTemplate) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
        this.participatesDao = participatesDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE PARTICIPATES IF EXISTS");
        jdbcTemplate.execute("DROP TABLE GAME IF EXISTS");
        jdbcTemplate.execute("DROP TABLE PLAYER IF EXISTS" );

        jdbcTemplate.execute("CREATE TABLE GAME ( " +
                "id          BIGINT   NOT NULL AUTO_INCREMENT, " +
                "trial_count INT      NOT NULL, " +
                "created_at  DATETIME NOT NULL default current_timestamp, " +
                "PRIMARY KEY (id))");

        jdbcTemplate.execute("CREATE TABLE PLAYER ( " +
                "id   BIGINT      NOT NULL AUTO_INCREMENT, " +
                "name varchar(10) NOT NULL, " +
                "PRIMARY KEY (id)) ");

        jdbcTemplate.execute("CREATE TABLE PARTICIPATES ( " +
                "game_id   BIGINT  NOT NULL, " +
                "player_id BIGINT  NOT NULL, " +
                "position  INT     NOT NULL, " +
                "is_winner BOOLEAN NOT NULL default false, " +
                "PRIMARY KEY (game_id, player_id), " +
                "FOREIGN KEY (game_id) references GAME (id), " +
                "FOREIGN KEY (player_id) references PLAYER (id)) ");

        gameDao.save(10);
        playerDao.save("망고");
        playerDao.save("루카");
    }

    @DisplayName("위치와 최종 승패를 입력받아 저장한다.")
    @Test
    void save() {
        ParticipateDto mangoDto = new ParticipateDto(1L, 1L, 10, true);
        ParticipateDto lucaDto = new ParticipateDto(1L, 2L, 3, false);
        //when
        participatesDao.save(mangoDto);
        participatesDao.save(lucaDto);
        //then
        String sql = "SELECT position FROM PARTICIPATES WHERE game_id = 1 and player_id = ?";
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class, 1L)).isEqualTo(10);
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class, 2L)).isEqualTo(3);
    }
}
