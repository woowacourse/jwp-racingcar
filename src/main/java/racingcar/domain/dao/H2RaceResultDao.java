package racingcar.domain.dao;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.domain.dao.entity.RaceResultEntity;

@Component
public class H2RaceResultDao implements RaceResultDao {

    private final JdbcTemplate jdbcTemplate;

    public H2RaceResultDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static Map<Long, RaceResultEntity> makeRaceResultEntities(
        final List<RaceResultCarJoinDto> raceResultCarJoinDtos) {
        final Map<Long, RaceResultEntity> raceResultEntities = new HashMap<>();
        for (final RaceResultCarJoinDto raceResultCarJoinDto : raceResultCarJoinDtos) {
            final Long raceResultId = raceResultCarJoinDto.getRaceResultId();
            if (raceResultEntities.containsKey(raceResultId)) {
                final RaceResultEntity raceResultEntity = raceResultEntities.get(raceResultId);
                raceResultEntity.addCarEntity(raceResultCarJoinDto.makeCarEntity());
                continue;
            }
            raceResultEntities.put(raceResultId, raceResultCarJoinDto.makeRaceResultEntity());
        }
        return raceResultEntities;
    }

    public Long save(final int trialCount, final String winners) {
        final String query = "INSERT INTO race_result (trial_count, winners, created_at) VALUES (?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(query,
                new String[]{"race_result_id"});
            preparedStatement.setString(1, String.valueOf(trialCount));
            preparedStatement.setString(2, winners);
            preparedStatement.setString(3, LocalDateTime.now().toString());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<RaceResultEntity> findAll() {
        final String query = "SELECT result.race_result_id,"
            + " result.trial_count,"
            + " result.winners,"
            + " car.car_id,"
            + " car.name,"
            + " car.position"
            + " FROM RACE_RESULT AS result"
            + " JOIN CAR AS car"
            + " ON result.race_result_id = car.race_result_id";
        final List<RaceResultCarJoinDto> raceResultCarJoinDtos = makeRaceResultCarJoinDtos(query);
        final Map<Long, RaceResultEntity> raceResultEntities = makeRaceResultEntities(
            raceResultCarJoinDtos);
        return raceResultEntities.values()
            .stream()
            .collect(Collectors.toUnmodifiableList());
    }

    private List<RaceResultCarJoinDto> makeRaceResultCarJoinDtos(final String query) {
        return jdbcTemplate.query(query,
            (resultSet, rowNum) -> {
                final long raceResultId = resultSet.getLong("race_result_id");
                final int trialCount = resultSet.getInt("trial_count");
                final String winners = resultSet.getString("winners");
                final long carId = resultSet.getLong("car_id");
                final String carName = resultSet.getString("name");
                final int carPosition = resultSet.getInt("position");
                return new RaceResultCarJoinDto(raceResultId, trialCount, winners, carId, carName,
                    carPosition);
            });
    }
}
