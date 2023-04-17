package racingcar.dao;

import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.cars.RacingCar;

@Repository
public class RacingCarRecordDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RacingCarRecordDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long insert(long racingHistoryId, RacingCar racingCar, boolean isWinner) {
        String sql = "INSERT INTO car_record (history_id, name, position, is_winner) VALUES (:historyId, :name, :position, :isWinner)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(
                        Map.of("historyId", racingHistoryId,
                                "name", racingCar.getName(),
                                "position", racingCar.getPosition(),
                                "isWinner", isWinner)),
                keyHolder);
        return keyHolder.getKey().longValue();
    }

}
