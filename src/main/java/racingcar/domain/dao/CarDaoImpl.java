package racingcar.domain.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.dao.entity.CarEntity;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDaoImpl(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveAll(final Long raceResultId, final List<Car> cars) {
        cars.forEach(car -> save(raceResultId, car));
    }

    @Override
    public List<CarEntity> findAll(final Long resultId) {
        final String query = "SELECT * FROM car WHERE race_result_id = ?";
        return jdbcTemplate.query(query, (result, count) ->
                new CarEntity(result.getLong("car_id"), result.getString("name"),
                        result.getInt("position")), resultId);
    }

    private void save(final Long raceResultId, final Car car) {
        final String query = "INSERT INTO car (name, position, race_result_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, car.getName(), car.getPosition(), raceResultId);
    }
}
