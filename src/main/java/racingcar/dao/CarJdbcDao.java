package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.CarEntity;

@Component
public class CarJdbcDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(List<CarEntity> racingCars) {
        String sql = "INSERT INTO CAR (name, position, is_win, racing_game_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CarEntity carEntity = racingCars.get(i);
                ps.setString(1, carEntity.getName());
                ps.setInt(2, carEntity.getPosition());
                ps.setBoolean(3, carEntity.isWin());
                ps.setLong(4, carEntity.getGameId());
            }

            @Override
            public int getBatchSize() {
                return racingCars.size();
            }
        });
    }
}
