package racingcar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.GamePlayResponseDto;
import racingcar.model.Car;

@Repository
public class InMemoryCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InMemoryCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(int gameId, List<Car> cars, List<String> winners) {
        String sql = "insert into CAR_RESULT (play_result_id, car_name, car_position, is_winner) values (?, ?, ?, ?)";

        for (Car car : cars) {
            final boolean isWinner = winners.contains(car.getName());
            jdbcTemplate.update(sql, gameId, car.getName(), car.getPosition(), isWinner);
        }
    }

    @Override
    public List<GamePlayResponseDto> selectAll() {
        String sql = "SELECT play_result_id, car_name, car_position, is_winner FROM CAR_RESULT";

        return jdbcTemplate.query(sql, getGamePlayResponseExtractor);
    }

    private final ResultSetExtractor<List<GamePlayResponseDto>> getGamePlayResponseExtractor = resultSet -> {
        final Map<Integer, GamePlayResponseDto> historyByGame = new HashMap<>();
        while (resultSet.next()) {
            final int gameId = resultSet.getInt("play_result_id");
            final String carName = resultSet.getString("car_name");
            final int carPosition = resultSet.getInt("car_position");
            final boolean isWinner = resultSet.getBoolean("is_winner");
            GamePlayResponseDto gameResult = getGameResult(historyByGame, gameId);
            if (isWinner) {
                gameResult.getWinners().add(carName);
            }
            final List<CarDto> racingCars = gameResult.getRacingCars();
            racingCars.add(new CarDto(carName, carPosition));
        }
        return new ArrayList<>(historyByGame.values());
    };

    private GamePlayResponseDto getGameResult(final Map<Integer, GamePlayResponseDto> resultByGameId,
                                              final int gameId) {
        GamePlayResponseDto gameResult = resultByGameId.get(gameId);
        if (gameResult == null) {
            gameResult = new GamePlayResponseDto();
            resultByGameId.put(gameId, gameResult);
        }
        return gameResult;
    }
}
