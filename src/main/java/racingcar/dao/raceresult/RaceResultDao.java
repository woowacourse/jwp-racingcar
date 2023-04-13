package racingcar.dao.raceresult;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;
import racingcar.domain.Car;

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

    public int save(final RaceResultRegisterRequest raceResultRegisterRequest) {

        final SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("RACE_RESULT").usingGeneratedKeyColumns("id");

        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("trial_count", raceResultRegisterRequest.getTrialCount())
                .addValue("winners", raceResultRegisterRequest.getWinners())
                .addValue("created_at", LocalDateTime.now());

        return jdbcInsert.executeAndReturnKey(params).intValue();
    }

    public Map<String, List<Car>> findAllRaceResult() {

        final String sql =
                "select r.winners, c.name, c.position"
                        + " from CAR c"
                        + " inner join RACE_RESULT r"
                        + " on r.id = c.race_result_id;";

        RowMapper<Map<String, List<Car>>> raceResultRowMapper = (resultSet, rowNum) -> {
            Map<String, List<Car>> raceResult = new HashMap<>();

            while (hasNext(resultSet)) {
                convertResultSetToRaceResult(resultSet, raceResult);
                resultSet.next();
            }

            return raceResult;
        };

        return jdbcTemplate.queryForObject(sql, raceResultRowMapper);
    }

    private boolean hasNext(final ResultSet resultSet) throws SQLException {
        return !resultSet.isAfterLast();
    }

    private void convertResultSetToRaceResult(final ResultSet resultSet, final Map<String, List<Car>> raceResult) {
        try {
            final String winners = resultSet.getString("winners");
            final String name = resultSet.getString("name");
            final int position = resultSet.getInt("position");

            if (!raceResult.containsKey(winners)) {
                raceResult.put(winners, new ArrayList<>());
            }

            raceResult.get(winners)
                      .add(new Car(name, position));

        } catch (SQLException exception) {
            throw new IllegalStateException("DB 조회 오류");
        }
    }
}
