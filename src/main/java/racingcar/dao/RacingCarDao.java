package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.entity.RacingCarEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RacingCarDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(final List<RacingCarEntity> racingCarEntities) {
        final String sql = "INSERT INTO RACING_CAR (game_id, name, position) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, racingCarEntities.get(i).getGameId());
                ps.setString(2, racingCarEntities.get(i).getName());
                ps.setInt(3, racingCarEntities.get(i).getPosition());
            }

            @Override
            public int getBatchSize() {
                return racingCarEntities.size();
            }
        });
    }

    public List<RacingCarEntity> findAllByGameId(final Long gameId) {
        final String sql = "SELECT * FROM RACING_CAR WHERE game_id = ?";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> RacingCarEntity.builder()
                        .id(rs.getLong(1))
                        .gameId(rs.getLong(2))
                        .name(rs.getString(3))
                        .position(rs.getInt(4))
                        .build(),
                gameId
        );
    }
}
