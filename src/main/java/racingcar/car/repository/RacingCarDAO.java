package racingcar.car.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.car.interfaces.Car;
import racingcar.car.interfaces.CarDAO;
import racingcar.car.model.RacingCar;

@Repository
public class RacingCarDAO implements CarDAO {
    
    private final RowMapper<Car> carRowMapper = (resultSet, rowNum) -> RacingCar.create(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    
    public RacingCarDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("racing_car")
                .usingGeneratedKeyColumns("id");
    }
    
    @Override
    public void insert(final Car car, final int gameId) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", car.getName().getValue());
        parameters.put("position", car.getPosition().getValue());
        parameters.put("racing_game_id", gameId);
        this.simpleJdbcInsert.execute(parameters);
    }
    
    @Override
    public void insertAll(final List<Car> cars, final int gameId) {
        cars.forEach(car -> this.insert(car, gameId));
    }
    
    @Override
    public Car findByName(final String name, final int gameId) {
        final String sql = "select name, position from racing_car where name = ? and racing_game_id = ?";
        return this.jdbcTemplate.queryForObject(sql, this.carRowMapper, name, gameId);
    }
    
    @Override
    public List<Car> findAll(final int gameId) {
        final String sql = "select name, position from racing_car where racing_game_id = ?";
        return this.jdbcTemplate.query(sql, this.carRowMapper, gameId);
    }
}
