package racingcar.domain.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.dao.entity.CarEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveAll(final Long raceResultId, final List<Car> cars) {
        final String query = "INSERT INTO car (name, position, race_result_id) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                final Car car = cars.get(i);
                ps.setString(1, car.getName());
                ps.setInt(2, car.getPosition());
                ps.setLong(3, raceResultId);
            }

            @Override
            public int getBatchSize() {
                return cars.size();
            }
        });
    }

    public List<CarEntity> findAll(final Long resultId) {
        final String query = "SELECT * FROM car WHERE race_result_id = ?";
        return jdbcTemplate.query(query, (result, count) ->
                new CarEntity(result.getLong("car_id"), result.getString("name"),
                        result.getInt("position")), resultId);
    }
}
