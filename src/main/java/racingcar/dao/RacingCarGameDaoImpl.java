package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.car.Car;
import racingcar.domain.game.RacingCarGame;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class RacingCarGameDaoImpl implements RacingCarGameDao {

    private static final String CAR_NAME_DELIMITER = ",";

    private final SimpleJdbcInsert simpleJdbcInsertCars;
    private final SimpleJdbcInsert simpleJdbcInsertGames;
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Car> carRowMapper = (resultSet, rowNum) -> Car.createCar(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    @Autowired
    public RacingCarGameDaoImpl(DataSource dataSource) {
        this.simpleJdbcInsertCars = new SimpleJdbcInsert(dataSource).withTableName("cars");
        this.simpleJdbcInsertGames = new SimpleJdbcInsert(dataSource).withTableName("games")
                                                                     .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(final RacingCarGame racingCarGame, final int count) {
        final String winners = toString(racingCarGame.getWinners());

        final Map<String, Object> gameParameters = Map.of("count", count, "winners", winners, "play_time", new Timestamp(System.currentTimeMillis()));
        final Number key = simpleJdbcInsertGames.executeAndReturnKey(gameParameters);

        final int gameId = key.intValue();
        final List<Car> carList = racingCarGame.getCars();
        for (Car car : carList) {
            final Map<String, Object> carParameters = Map.of("name", car.getName(), "position", car.getPosition(), "game_id", gameId);
            simpleJdbcInsertCars.execute(carParameters);
        }

        return gameId;
    }

    @Override
    public List<Car> findCarsByGameId(final int gameId) {
        String carsQuery = "SELECT name, position FROM cars WHERE game_id = ?";
        return jdbcTemplate.query(carsQuery, carRowMapper, gameId);
    }

    @Override
    public List<String> findWinners(final int gameId) {
        String gameWinnerQuery = "SELECT winners FROM games WHERE id = ?";
        final String winners = jdbcTemplate.queryForObject(gameWinnerQuery, String.class, gameId);
        return toList(Objects.requireNonNull(winners, "존재하지 않는 게임아이디입니다"));
    }

    @Override
    public List<Integer> findAllGameIds() {
        String gameIdsQuery = "SELECT id FROM games";
        return jdbcTemplate.query(gameIdsQuery, (resultSet, rowNum) -> resultSet.getInt("id"));
    }

    private String toString(final List<Car> winners) {
        return winners.stream()
                      .map(Car::getName)
                      .collect(Collectors.joining(CAR_NAME_DELIMITER));
    }

    private List<String> toList(final String winnersString) {
        return new ArrayList<>(List.of(winnersString.split(CAR_NAME_DELIMITER)));
    }
}
