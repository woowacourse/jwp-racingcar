package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.domain.Car;

@Component
class CarDao {
    private final JdbcTemplate jdbcTemplate;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(final int gameId, final List<Car> cars) {
        final String sql = "INSERT INTO car(name, position, game_id) VALUES (?, ?, ?)";
        final BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                final Car car = cars.get(i);
                ps.setString(1, car.getName());
                ps.setInt(2, car.getPosition());
                ps.setInt(3, gameId);
            }

            @Override
            public int getBatchSize() {
                return cars.size();
            }
        };
        jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetter);
    }
}
