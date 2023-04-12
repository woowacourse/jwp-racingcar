package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.entity.RacingGameEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class RacingCarDao {
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Car> carRowMapper = (resultSet, rowNum) -> new Car(resultSet.getString("name"), resultSet.getInt("position"));

    private final RowMapper<RacingGameEntity> rowMapper = (resultSet, rowNum) -> {
        int gameId = resultSet.getInt("id");
        String sql = "SELECT name, position FROM RACING_CAR WHERE racing_game_id = ?";
        List<Car> cars = jdbcTemplate.query(sql, carRowMapper, gameId);
        return new RacingGameEntity.Builder()
                .racingCars(cars)
                .count(resultSet.getInt("count"))
                .winners(resultSet.getString("winners"))
                .build();
    };

    @Autowired
    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(RacingGameEntity racingGameEntity) {
        String sql = "INSERT INTO RACING_GAME(count, winners, created_at) VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, Integer.toString(racingGameEntity.getCount()));
            preparedStatement.setString(2, racingGameEntity.getWinners());
            preparedStatement.setString(3, racingGameEntity.getCreatedAt().toString());
            return preparedStatement;
        }, keyHolder);
        int id = (int) keyHolder.getKey();
        String sqlForRacingGameEntity = "INSERT INTO RACING_CAR(position, name, racing_game_id) VALUES(?, ?, ?)";
        racingGameEntity.getRacingCars().stream()
                .forEach(car -> jdbcTemplate.update(sqlForRacingGameEntity, car.getPosition(), car.getName(), id));
    }

    public List<RacingGameEntity> findAll() {
        String sql = "SELECT id, winners, count FROM RACING_GAME";
        return jdbcTemplate.query(sql, rowMapper);
    }

}
