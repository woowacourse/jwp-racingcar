package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcPlayerDao implements PlayerDao {

    private final SimpleJdbcInsert insertPlayerActor;
    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayerDao(JdbcTemplate jdbcTemplate) {
        this.insertPlayerActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("PLAYER")
                .usingGeneratedKeyColumns("player_id");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(int gameId, List<Car> cars) {
        SqlParameterSource[] batchParams = new SqlParameterSource[cars.size()];
        for (int index = 0; index < cars.size(); index++) {
            Car car = cars.get(index);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("game_id", gameId);
            parameters.put("name", car.getName());
            parameters.put("position", car.getDistance());
            batchParams[index] = new MapSqlParameterSource(parameters);
        }

        insertPlayerActor.executeBatch(batchParams);
    }

    private final RowMapper<Car> carRowMapper = (resultSet, rowNum) -> {
        Car car = new Car(resultSet.getString("name"), resultSet.getInt("position"));
        return car;
    };

    @Override
    public List<Car> find(int gameId) {
        String sql = "SELECT name, position FROM PLAYER WHERE game_id = ?";
        return jdbcTemplate.query(sql, carRowMapper, gameId);
    }
}
