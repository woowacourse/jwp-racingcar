package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import racingcar.dao.entity.ParticipantEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class ParticipantJdbcTemplateDaoTest {

    private final ParticipantJdbcTemplateDao participantJdbcTemplateDao;
    private final JdbcTemplate jdbcTemplate;

    private Long firstGameId;
    private Long secondGameId;
    private Long mangoId;
    private Long lucaId;

    @Autowired
    public ParticipantJdbcTemplateDaoTest(final JdbcTemplate jdbcTemplate) {
        this.participantJdbcTemplateDao = new ParticipantJdbcTemplateDao(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        final GameJdbcTemplateDao gameJdbcTemplateDao = new GameJdbcTemplateDao(jdbcTemplate);
        final PlayerJdbcTemplateDao playerJdbcTemplateDao = new PlayerJdbcTemplateDao(jdbcTemplate);
        firstGameId = gameJdbcTemplateDao.save(10);
        secondGameId = gameJdbcTemplateDao.save(20);
        mangoId = playerJdbcTemplateDao.save("망고");
        lucaId = playerJdbcTemplateDao.save("루카");
        final String sql = "INSERT INTO PARTICIPANT(game_id, player_id, position, is_winner) VALUES(?, ?, ?, ?) ";
        jdbcTemplate.update(sql, firstGameId, mangoId, 7, true);
        jdbcTemplate.update(sql, firstGameId, lucaId, 5, false);
        jdbcTemplate.update(sql, secondGameId, mangoId, 10, true);
        jdbcTemplate.update(sql, secondGameId, lucaId, 5, false);
    }

    @DisplayName("모든 참가자를 조회한다.")
    @Test
    @Order(1)
    void findAll() {
        //when
        List<ParticipantEntity> allParticipant = participantJdbcTemplateDao.findAll();
        //then
        assertThat(allParticipant).hasSize(4);
    }

    @DisplayName("특정 게임에 대한 참가자를 조회한다.")
    @Test
    @Order(2)
    void findByGameId() {
        //when
        List<ParticipantEntity> allParticipant = participantJdbcTemplateDao.findByGameId(firstGameId);
        //then
        assertThat(allParticipant).hasSize(2);
    }

    @DisplayName("위치와 최종 승패를 입력받아 저장한다.")
    @Test
    @Order(3)
    void save() {
        //given
        String preSql = "INSERT INTO GAME(trial_count) VALUES(10)";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> connection.prepareStatement(preSql, new String[] {"id"}), generatedKeyHolder);
        Long gameId = (Long) generatedKeyHolder.getKey();
        ParticipantEntity mangoDto = new ParticipantEntity(gameId, mangoId, 10, true);
        ParticipantEntity lucaDto = new ParticipantEntity(gameId, lucaId, 3, false);
        //when
        participantJdbcTemplateDao.save(mangoDto);
        participantJdbcTemplateDao.save(lucaDto);
        //then
        String sql = "SELECT position FROM PARTICIPANT WHERE game_id = ? and player_id = ?";
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class, gameId, mangoId)).isEqualTo(10);
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class, gameId, lucaId)).isEqualTo(3);
    }
}
