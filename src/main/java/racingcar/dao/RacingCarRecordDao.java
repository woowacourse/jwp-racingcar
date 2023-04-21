package racingcar.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public Long insert(long racingHistoryId, RacingCar racingCar, boolean isWinner) {
        String sql = "INSERT INTO car_record (history_id, name, position, is_winner) VALUES (:historyId, :name, :position, :isWinner)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(
                        Map.of("historyId", racingHistoryId,
                                "name", racingCar.getName(),
                                "position", racingCar.getPosition(),
                                "isWinner", isWinner)),
                keyHolder);
        return keyHolder.getKeyAs(Long.class);
    }

    public List<RacingCarRecord> findByHistoryId(Long racingHistoryId) {
        return jdbcTemplate.query(
                "SELECT id, name, position, is_winner, history_id FROM car_record WHERE history_id = :id",
                new MapSqlParameterSource("id", racingHistoryId),
                (rs, rowNum) -> new RacingCarRecord(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("position"),
                        rs.getBoolean("is_winner"),
                        rs.getLong("history_id")
                )
        );

    }

    public void insertAll(Long historyId, Map<RacingCar, Boolean> racingCarsToResult) {
        String sql = "INSERT INTO car_record (history_id, name, position, is_winner) VALUES (?, ?, ?, ?)";
        Set<RacingCar> racingCars = racingCarsToResult.keySet();
        jdbcTemplate.getJdbcOperations()
                .batchUpdate(sql, racingCars, racingCars.size(), (preparedStatement, racingCar) -> {
                    preparedStatement.setLong(1, historyId);
                    preparedStatement.setString(2, racingCar.getName());
                    preparedStatement.setInt(3, racingCar.getPosition());
                    preparedStatement.setBoolean(4, racingCarsToResult.get(racingCar));
                });
    }
}
