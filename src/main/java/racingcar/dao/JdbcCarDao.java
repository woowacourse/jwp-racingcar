package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import racingcar.dto.CarEntity;

@Component
public class JdbcCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCarDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(int gameId, List<CarEntity> carEntities) {
        final String sql = "INSERT INTO CAR(game_id, name, position, is_win) VALUES (?,?,?,?)";
        final BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                final CarEntity carEntity = carEntities.get(i);
                ps.setInt(1, gameId);
                ps.setString(2, carEntity.getName());
                ps.setInt(3, carEntity.getPosition());
                ps.setBoolean(4, carEntity.isWin());

            }

            @Override
            public int getBatchSize() {
                return carEntities.size();
            }
        };

        jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetter);
    }

    @Override
    public List<CarEntity> findAll() {
        String sql = "SELECT game_id, name, position, is_win FROM CAR";
        return jdbcTemplate.query(sql, carEntityRowMapper());
    }

    private RowMapper<CarEntity> carEntityRowMapper() {
        return (rs, rowNum) -> {
            final int gameId = rs.getInt("game_id");
            final String name = rs.getString("name");
            final int position = rs.getInt("position");
            final boolean isWin = rs.getBoolean("is_win");

            return new CarEntity(gameId, name, position, isWin);
        };
    }

}
