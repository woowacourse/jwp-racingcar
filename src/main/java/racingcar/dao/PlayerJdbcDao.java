package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayerEntity;

@Repository
public class PlayerJdbcDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PlayerEntity> playerRowMapper = (resultSet, rowNum) ->
            new PlayerEntity(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("position"),
                    resultSet.getInt("game_id"),
                    resultSet.getBoolean("is_winner")
            );

    public PlayerJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(List<PlayerEntity> playerEntities) {

        String sql = "insert into PLAYER (name, position, game_id, is_winner) values (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                PlayerEntity playerEntity = playerEntities.get(i);
                ps.setString(1, playerEntity.getName());
                ps.setInt(2, playerEntity.getPosition());
                ps.setInt(3, playerEntity.getGameId());
                ps.setBoolean(4, playerEntity.isWinner());
            }

            @Override
            public int getBatchSize() {
                return playerEntities.size();
            }
        });
    }

    public List<PlayerEntity> findAll() {
        String sql = "select * from PLAYER";

        return jdbcTemplate.query(sql, playerRowMapper);
    }
}
