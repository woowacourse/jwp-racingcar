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
                .usingGeneratedKeyColumns("id");
    }

    public void insertPlayers(int gameId, List<Car> cars, List<String> winnerNames) {
        cars.forEach(car -> insertPlayer(gameId, car, isWinner(winnerNames, car.getName())));
    }

    private boolean isWinner(List<String> winnerNames, String name) {
        return winnerNames.contains(name);
    }

    private void insertPlayer(int gameId, Car car, boolean isWinner) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("game_id", gameId);
        parameters.put("name", car.getName());
        parameters.put("position", car.getDistance());
        parameters.put("is_winner", isWinner);
        insertPlayerActor.execute(parameters);
    }
}
