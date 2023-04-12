package racingcar.dao;

import java.util.List;
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
        String sql = "INSERT INTO car_record (game_id, name, position, is_winner) VALUES (:gameId, :name, :position, :isWinner)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(
                        Map.of("gameId", racingHistoryId,
                                "name", car.getName(),
                                "position", car.getPosition(),
                                "isWinner", isWinner)),
                keyHolder);
        return keyHolder.getKeyAs(Long.class);
    }

    public List<CarRecord> findByRacingHistoryId(long racingHistoryId) {
        return jdbcTemplate.query(
                "SELECT * FROM car_record WHERE game_id = :id",
                new MapSqlParameterSource("id", racingHistoryId),
                (rs, rowNum) -> new CarRecord(
                        rs.getString("name"),
                        rs.getInt("position"),
                        rs.getBoolean("is_winner")
                )
        );
    }
}
