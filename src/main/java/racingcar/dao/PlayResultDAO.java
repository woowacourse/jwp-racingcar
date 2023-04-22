package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.entity.PlayerResultEntity;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PlayResultDAO {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public PlayResultDAO(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("play_result")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    public int returnTableIdAfterInsert(Integer count, List<Car> winners) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("count", count);
        parameters.put("winners", makeWinnersString(winners));
        parameters.put("created_at", LocalDateTime.now());
        return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }

    private String makeWinnersString(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    public List<PlayerResultEntity> findAll() {
        String sql = "select * from play_result";

        return jdbcTemplate.query(sql, playerResultRowMapper());
    }

    private RowMapper<PlayerResultEntity> playerResultRowMapper() {
        return (result, columnRow) -> {
            final PlayerResultEntity playerResultEntity = new PlayerResultEntity(
                    result.getInt("id"),
                    result.getInt("count"),
                    result.getString("winners"),
                    result.getTimestamp("created_at")
            );
            return playerResultEntity;
        };
    }
}
