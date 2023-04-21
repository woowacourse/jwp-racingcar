package racingcar.dao.participates;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dto.ParticipateDto;
import racingcar.entity.ParticipatesEntity;

@Repository
public class WebParticipatesDao implements ParticipatesDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<ParticipatesEntity> rowMapper = (resultSet, rowNum) -> new ParticipatesEntity(
            resultSet.getLong("game_id"),
            resultSet.getLong("player_id"),
            resultSet.getInt("position"),
            resultSet.getBoolean("is_winner"));

    public WebParticipatesDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final ParticipateDto participateDto) {
        final String sql = "INSERT INTO PARTICIPATES(game_id, player_id, position, is_winner) VALUES(?, ?, ?, ?) ";
        jdbcTemplate.update(sql,
                participateDto.getGameId(),
                participateDto.getPlayerId(),
                participateDto.getPosition(),
                participateDto.getIsWinner()
        );
    }

    @Override
    public List<ParticipatesEntity> findByGameId(final Long gameId) {
        final String sql = "SELECT * FROM PARTICIPATES WHERE game_id = ?";
        return jdbcTemplate.query(sql, rowMapper, gameId);
    }
}
