package racingcar.dao;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

@Repository
public class InsertDao {

    private final SimpleJdbcInsert insertGameActor;
    private final SimpleJdbcInsert insertPlayerActor;

    public InsertDao(DataSource dataSource) {
        this.insertGameActor = new SimpleJdbcInsert(dataSource)
                .withTableName("GAME")
                .usingGeneratedKeyColumns("game_id")
                .usingColumns("winners", "trial_count");
        this.insertPlayerActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAYER")
                .usingGeneratedKeyColumns("player_id");
    }

    public void insert(String winners, Integer count, List<Car> cars) {
        int gameId = insertGame(winners, count);
        cars.forEach(car -> insertPlayer(gameId, car));
    }

    private int insertGame(String winners, Integer count) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("winners", winners);
        parameters.put("trial_count", count);
        return insertGameActor.executeAndReturnKey(parameters).intValue();
    }

    private void insertPlayer(int gameId, Car car) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("game_id", gameId);
        parameters.put("name", car.getName());
        parameters.put("position", car.getDistance());
        insertPlayerActor.execute(parameters);
    }
}
