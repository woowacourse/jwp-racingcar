package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.entity.ParticipantEntity;

import java.util.List;

@Repository
public class ParticipantDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParticipantDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ParticipantEntity> findByGameId(final Long gameId) {
        final String sql = "SELECT * FROM PARTICIPANT WHERE game_id = ?";
        RowMapper<ParticipantEntity> participantEntityRowMapper = (resultSet, rowNum) ->
                new ParticipantEntity(
                        resultSet.getLong("game_id"),
                        resultSet.getLong("player_id"),
                        resultSet.getInt("position"),
                        resultSet.getBoolean("is_winner")
                );
        return jdbcTemplate.query(sql, participantEntityRowMapper, gameId);
    }

    public void save(final ParticipateDto participateDto) {
        final String sql = "INSERT INTO PARTICIPANT(game_id, player_id, position, is_winner) VALUES(?, ?, ?, ?) ";
        jdbcTemplate.update(sql,
                participantEntity.getGameId(),
                participantEntity.getPlayerId(),
                participantEntity.getPosition(),
                participantEntity.getWinner()
        );
    }
}
