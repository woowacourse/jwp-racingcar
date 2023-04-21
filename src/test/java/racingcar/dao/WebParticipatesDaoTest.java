package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.ParticipateDto;
import racingcar.entity.ParticipatesEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WebParticipatesDaoTest {

    private final WebGameDao webGameDao;
    private final WebPlayerDao webPlayerDao;
    private final WebParticipatesDao webParticipatesDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WebParticipatesDaoTest(final WebGameDao webGameDao, final WebPlayerDao webPlayerDao,
                                  final WebParticipatesDao webParticipatesDao, final JdbcTemplate jdbcTemplate) {
        this.webGameDao = webGameDao;
        this.webPlayerDao = webPlayerDao;
        this.webParticipatesDao = webParticipatesDao;
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

        webGameDao.save(10);
        webPlayerDao.save("망고");
        webPlayerDao.save("루카");
    }

    @DisplayName("위치와 최종 승패를 입력받아 저장한다.")
    @Test
    void save() {
        ParticipateDto mangoDto = new ParticipateDto(1L, 1L, 10, true);
        ParticipateDto lucaDto = new ParticipateDto(1L, 2L, 3, false);
        //when
        webParticipatesDao.save(mangoDto);
        webParticipatesDao.save(lucaDto);
        //then
        String sql = "SELECT position FROM PARTICIPATES WHERE game_id = 1 and player_id = ?";
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class, 1L)).isEqualTo(10);
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class, 2L)).isEqualTo(3);
    }

    @DisplayName("game_id로 데이터를 조회할 수 있다.")
    @Test
    void findByGameId() {
        ParticipateDto mangoDto = new ParticipateDto(1L, 1L, 10, true);
        ParticipateDto lucaDto = new ParticipateDto(1L, 2L, 3, false);
        //when
        webParticipatesDao.save(mangoDto);
        webParticipatesDao.save(lucaDto);
        //then
        String sql = "SELECT * FROM PARTICIPATES WHERE game_id = ?";
        final List<ParticipatesEntity> result = jdbcTemplate.query(sql, (resultSet, rowNum) -> new ParticipatesEntity(
                        resultSet.getLong("game_id"),
                        resultSet.getLong("player_id"),
                        resultSet.getInt("position"),
                        resultSet.getBoolean("is_winner"))
                , 1L);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getPlayerId()).isEqualTo(1L);
        assertThat(result.get(0).getWinner()).isEqualTo(true);
    }
}
