package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.domain.Car;

@Repository
public class CarsDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public CarsDao(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("cars")
                .usingGeneratedKeyColumns("id");
    }

    public Map<Car, Integer> insert(final int gameId, final List<Car> cars) {
        final Map<Car, Integer> carIds = new HashMap<>();
        for (Car car : cars) {
            final SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("game_id", gameId)
                    .addValue("name", car.getName())
                    .addValue("position", car.getPosition());

            int carId = simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
            carIds.put(car, carId);
        }
        return carIds;
    }
}
