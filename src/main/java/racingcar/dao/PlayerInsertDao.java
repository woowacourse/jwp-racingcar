package racingcar.dao;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

@Repository
public class PlayerInsertDao {

    private final SimpleJdbcInsert insertPlayerActor;

    public PlayerInsertDao(DataSource dataSource) {
        this.insertPlayerActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAYER")
                .usingGeneratedKeyColumns("player_id");
    }

    public void insertPlayers(int gameId, List<Car> cars) {
        cars.forEach(car -> insertPlayer(gameId, car));
    }

    private void insertPlayer(int gameId, Car car) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("game_id", gameId);
        parameters.put("name", car.getName());
        parameters.put("position", car.getDistance());
        insertPlayerActor.execute(parameters);
    }
}
