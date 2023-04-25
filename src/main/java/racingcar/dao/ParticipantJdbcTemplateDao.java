package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.ParticipantEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ParticipantJdbcTemplateDao implements ParticipantDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParticipantJdbcTemplateDao(final JdbcTemplate jdbcTemplate) {
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

    public List<ParticipantEntity> findAll() {
        final String sql = "SELECT * FROM PARTICIPANT ";
        RowMapper<ParticipantEntity> participantEntityRowMapper = (resultSet, rowNum) ->
                new ParticipantEntity(
                        resultSet.getLong("game_id"),
                        resultSet.getLong("player_id"),
                        resultSet.getInt("position"),
                        resultSet.getBoolean("is_winner")
                );
        return jdbcTemplate.query(sql, participantEntityRowMapper);
    }

    @Override
    public void saveAll(final List<ParticipantEntity> participantEntities) {
        final String sql = "INSERT INTO PARTICIPANT(game_id, player_id, position, is_winner) VALUES(?, ?, ?, ?) ";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, participantEntities.get(i).getGameId());
                ps.setLong(2, participantEntities.get(i).getPlayerId());
                ps.setInt(3, participantEntities.get(i).getPosition());
                ps.setBoolean(4, participantEntities.get(i).getWinner());
            }

            @Override
            public int getBatchSize() {
                return participantEntities.size();
            }
        });
    }
}
