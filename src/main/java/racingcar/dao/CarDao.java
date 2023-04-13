package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

@Repository
public class CarDao {

    private RowMapper<Car> actorRowMapper = (resultSet, rowNum) -> {
        Car car = new Car(
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
        return car;
    };

    private final JdbcTemplate jdbcTemplate;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final long id, final List<Car> cars) {
        jdbcTemplate.batchUpdate("INSERT INTO car (play_result_id, name, position) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                        ps.setLong(1, id);
                        ps.setString(2, cars.get(i).getName());
                        ps.setInt(3, cars.get(i).getPosition());
                    }

                    @Override
                    public int getBatchSize() {
                        return cars.size();
                    }
                });
    }

    public List<Car> find(final long id) {
        return jdbcTemplate.query("SELECT name, position FROM car WHERE play_result_id = ?", actorRowMapper, id);
    }
}
