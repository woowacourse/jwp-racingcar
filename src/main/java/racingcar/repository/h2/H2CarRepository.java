package racingcar.repository.h2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.entity.CarEntity;
import racingcar.repository.CarRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class H2CarRepository implements CarRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public H2CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("car")
                .usingGeneratedKeyColumns("id");
    }

    private RowMapper<CarEntity> carRowMapper() {
        return (resultSet, rowNum) ->
                CarEntity.of(
                        resultSet.getLong("id"),
                        resultSet.getLong("game_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("position")
                );
    }

    public CarEntity save(CarEntity carEntity) {
        Map<String, Object> map = new HashMap<>();
        map.put("game_id", carEntity.getGameId());
        map.put("name", carEntity.getName());
        map.put("position", carEntity.getPosition());

        SqlParameterSource source = new MapSqlParameterSource(map);
        long id = simpleJdbcInsert.executeAndReturnKey(source).longValue();

        return findById(id);
    }

    public CarEntity findById(long id) {
        String sql = "SELECT * FROM car WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, carRowMapper(), id);
    }

    public List<CarEntity> findAllByGameId(long id) {
        String sql = "SELECT * FROM car WHERE game_id = ?";
        return jdbcTemplate.query(sql, carRowMapper(), id);
    }
}
