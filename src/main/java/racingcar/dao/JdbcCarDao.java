package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import racingcar.domain.entity.CarEntity;

@Component
public class JdbcCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CarEntity> rowMapper = (rs, rowNum) -> {
        int id = rs.getInt("id");
        int gameId = rs.getInt("racing_game_id");
        String name = rs.getString("name");
        int position = rs.getInt("position");
        boolean isWin = rs.getBoolean("is_win");

        return new CarEntity(id, gameId, name, position, isWin);
    };

    public JdbcCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(int gameId, List<CarEntity> carEntities) {
        String sql = "INSERT INTO CAR(racing_game_id, name, position, is_win) VALUES (?,?,?,?)";
        BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CarEntity carEntity = carEntities.get(i);
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
        String sql = "SELECT * FROM CAR";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
