package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import racingcar.dao.entity.ParticipantEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class ParticipantJdbcTemplateDaoTest {

    private final GameJdbcTemplateDao gameJdbcTemplateDao;
    private final PlayerJdbcTemplateDao playerJdbcTemplateDao;
    private final ParticipantJdbcTemplateDao participantJdbcTemplateDao;
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ParticipantJdbcTemplateDaoTest(final JdbcTemplate jdbcTemplate) {
        this.gameJdbcTemplateDao = new GameJdbcTemplateDao(jdbcTemplate);
        this.playerJdbcTemplateDao = new PlayerJdbcTemplateDao(jdbcTemplate);
        this.participantJdbcTemplateDao = new ParticipantJdbcTemplateDao(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @DisplayName("모든 참가자를 조회한다.")
    @Test
    void findAll() {
        //given
        final Long firstGameId = gameJdbcTemplateDao.save(10);
        final Long secondGameId = gameJdbcTemplateDao.save(20);
        final Long mangoId = playerJdbcTemplateDao.save("망고");
        final Long lucaId = playerJdbcTemplateDao.save("루카");
        final String sql = "INSERT INTO PARTICIPANT(game_id, player_id, position, is_winner) VALUES(?, ?, ?, ?) ";
        jdbcTemplate.update(sql, firstGameId, mangoId, 7, true);
        jdbcTemplate.update(sql, firstGameId, lucaId, 5, false);
        jdbcTemplate.update(sql, secondGameId, mangoId, 10, true);
        jdbcTemplate.update(sql, secondGameId, lucaId, 5, false);
        //when
        List<ParticipantEntity> allParticipant = participantJdbcTemplateDao.findAll();
        //then
        assertThat(allParticipant).hasSize(4);
    }

    @DisplayName("특정 게임에 대한 참가자를 조회한다.")
    @Test
    void findByGameId() {
        //given
        final Long firstGameId = gameJdbcTemplateDao.save(10);
        final Long secondGameId = gameJdbcTemplateDao.save(20);
        final Long mangoId = playerJdbcTemplateDao.save("망고");
        final Long lucaId = playerJdbcTemplateDao.save("루카");
        final String sql = "INSERT INTO PARTICIPANT(game_id, player_id, position, is_winner) VALUES(?, ?, ?, ?) ";
        jdbcTemplate.update(sql, firstGameId, mangoId, 7, true);
        jdbcTemplate.update(sql, firstGameId, lucaId, 5, false);
        jdbcTemplate.update(sql, secondGameId, mangoId, 10, true);
        jdbcTemplate.update(sql, secondGameId, lucaId, 5, false);
        //when
        List<ParticipantEntity> allParticipant = participantJdbcTemplateDao.findByGameId(firstGameId);
        //then
        assertThat(allParticipant).hasSize(2);
    }

    @DisplayName("위치와 최종 승패 정보로 Participant들을 저장한다.")
    @Test
    void saveAll() {
        //given
        String preSql = "INSERT INTO GAME(trial_count) VALUES(10)";
        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> connection.prepareStatement(preSql, new String[]{"id"}), generatedKeyHolder);
        final Long gameId = (Long) generatedKeyHolder.getKey();
        final Long mangoId = playerJdbcTemplateDao.save("망고");
        final Long lucaId = playerJdbcTemplateDao.save("루카");
        //when
        List<ParticipantEntity> participantEntities = new ArrayList<>();
        ParticipantEntity mango = new ParticipantEntity(gameId, mangoId, 10, true);
        participantEntities.add(mango);
        ParticipantEntity luca = new ParticipantEntity(gameId, lucaId, 3, false);
        participantEntities.add(luca);
        participantJdbcTemplateDao.saveAll(participantEntities);
        //then
        String sql = "SELECT * FROM PARTICIPANT WHERE game_id = ? ";
        List<ParticipantEntity> result = jdbcTemplate.query(sql, (rs, rowNum) -> new ParticipantEntity(
                rs.getLong(1),
                rs.getLong(2),
                rs.getInt(3),
                rs.getBoolean(4)
        ), gameId);
        assertThat(result).contains(luca, mango);
    }
}
