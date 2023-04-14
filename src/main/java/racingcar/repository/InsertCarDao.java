package racingcar.repository;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.domain.Car;

public class InsertCarDao {

    private final SimpleJdbcInsert insertActor;

    public InsertCarDao(final DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("car")
                .usingGeneratedKeyColumns("car_id");
    }

    public CarEntity save(final Car car, final int gameId) {
        final Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("name", car.getCarName());
        parameters.put("position", car.getPosition());
        parameters.put("game_id", gameId);

        return new CarEntity(car, insertActor.executeAndReturnKey(parameters).intValue());
    }
}
