package racingcar.car.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.car.interfaces.Car;
import racingcar.car.interfaces.CarDAO;

@Repository
public class RacingCarDAO implements CarDAO {
    
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
}
