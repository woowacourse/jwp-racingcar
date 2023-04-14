package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.model.Car;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InMemoryCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public InMemoryCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(int gameId, List<Car> cars) {
        String sql = "insert into CAR_RESULT (play_result_id, car_name, car_position) values (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,gameId);
                ps.setString(2, cars.get(i).getName());
                ps.setInt(3, cars.get(i).getLocation());
            }
            @Override
            public int getBatchSize() {
                return cars.size();
            }
        });
    }
}
