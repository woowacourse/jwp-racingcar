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
class ParticipantDaoTest {

    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final ParticipantDao participantDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParticipantDaoTest(final GameDao gameDao, final PlayerDao playerDao, final ParticipantDao participantDao, final JdbcTemplate jdbcTemplate) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
        this.participantDao = participantDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        gameDao.save(10);
        playerDao.save("망고");
        playerDao.save("루카");
    }

    @DisplayName("위치와 최종 승패를 입력받아 저장한다.")
    @Test
    void save() {
        //given
        String preSql = "INSERT INTO GAME(trial_count) VALUES(10)";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> connection.prepareStatement(preSql, new String[] {"id"}), generatedKeyHolder);
        Long gameId = (Long) generatedKeyHolder.getKey();
        ParticipantEntity mangoDto = new ParticipantEntity(gameId, mangoId, 10, true);
        ParticipantEntity lucaDto = new ParticipantEntity(gameId, lucaId, 3, false);
        //when
        participantDao.save(mangoDto);
        participantDao.save(lucaDto);
        //then
        String sql = "SELECT position FROM PARTICIPANT WHERE game_id = 1 and player_id = ?";
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class, 1L)).isEqualTo(10);
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class, 2L)).isEqualTo(3);
    }
}
