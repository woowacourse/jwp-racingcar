package racingcar.dao;

import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.car.Car;

@Repository
public class CarRecordDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CarRecordDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(long racingHistoryId, Car car, boolean isWinner) {
        String sql = "INSERT INTO car_record (history_id, name, position, is_winner) VALUES (:historyId, :name, :position, :isWinner)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(
                        Map.of("historyId", racingHistoryId,
                                "name", car.getName(),
                                "position", car.getPosition(),
                                "isWinner", isWinner)),
                keyHolder);
        return keyHolder.getKeyAs(Long.class);
    }

}
