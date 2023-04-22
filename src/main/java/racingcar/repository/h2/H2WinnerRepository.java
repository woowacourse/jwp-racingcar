package racingcar.repository.h2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;
import racingcar.repository.WinnerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class H2WinnerRepository implements WinnerRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public H2WinnerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winner")
                .usingGeneratedKeyColumns("id");
    }

    private RowMapper<CarEntity> winnerRowMapper() {
        return (resultSet, rowNum) ->
                CarEntity.of(
                        resultSet.getLong("id"),
                        resultSet.getLong("game_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("position")
                );
    }

    @Override
    public CarEntity save(CarEntity entity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("car_id", entity.getId());
        long id = simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
        return entity;
    }

    @Override
    public List<CarEntity> findWinnersByGameId(long id) {
        String sql = "SELECT * FROM car " +
                "JOIN game ON game.id = car.game_id " +
                "JOIN winner ON car.id = winner.car_id " +
                "WHERE game.id = ?";
        return jdbcTemplate.query(sql, winnerRowMapper(), id);
    }
}
