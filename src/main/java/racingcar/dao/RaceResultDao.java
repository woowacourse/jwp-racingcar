package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;
import racingcar.entity.RaceResultEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RaceResultDao {

    private final JdbcTemplate jdbcTemplate;

    public RaceResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(final RaceResultEntity raceResultEntity) {

        final SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("RACE_RESULT").usingGeneratedKeyColumns("id");

        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("trial_count", raceResultEntity.getTrialCount())
                .addValue("winners", raceResultEntity.getWinners())
                .addValue("created_at", LocalDateTime.now());

        return jdbcInsert.executeAndReturnKey(params).intValue();
    }

    public Map<String, List<CarEntity>> findAllRaceResult() {

        final String sql =
                "select r.winners, c.name, c.position, c.created_at, c.race_result_id"
                        + " from RACE_RESULT r"
                        + " inner join Car c"
                        + " on r.id = c.race_result_id;";

        return jdbcTemplate.query(sql, (ResultSet rs) -> {
            Map<String, List<CarEntity>> resultMap = new HashMap<>();

            while (rs.next()) {
                final String winners = rs.getString("winners");

                final CarEntity carEntity = new CarEntity(
                        rs.getString("name"),
                        rs.getInt("position"),
                        rs.getInt("race_result_id"),
                        rs.getTimestamp("created_at")
                          .toLocalDateTime()
                );

                if (!resultMap.containsKey(winners)) {
                    resultMap.put(winners, new ArrayList<>());
                }

                resultMap.get(winners)
                         .add(carEntity);
            }

            return resultMap;
        });
    }
}
