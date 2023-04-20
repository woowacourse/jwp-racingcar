package racingcar.dao.car;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.car.dto.CarRegisterRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(final List<CarRegisterRequest> carRegisterRequests) {
        final SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("CAR")
                  .usingGeneratedKeyColumns("id");

        List<MapSqlParameterSource> batchInsertData = makeBatchInsertDataFrom(carRegisterRequests);

        jdbcInsert.executeBatch(mapToArrayFrom(batchInsertData));
    }

    private static MapSqlParameterSource[] mapToArrayFrom(final List<MapSqlParameterSource> batchInsertData) {
        return batchInsertData.toArray(new MapSqlParameterSource[0]);
    }

    private static List<MapSqlParameterSource> makeBatchInsertDataFrom(
            final List<CarRegisterRequest> carRegisterRequests
    ) {

        List<MapSqlParameterSource> mapSqlParameterSources = new ArrayList<>();

        for (final CarRegisterRequest carRegisterRequest : carRegisterRequests) {

            final MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

            mapSqlParameterSource.addValue("name", carRegisterRequest.getName())
                                 .addValue("position", carRegisterRequest.getPosition())
                                 .addValue("race_result_id", carRegisterRequest.getPlayResultId())
                                 .addValue("created_at", LocalDateTime.now());

            mapSqlParameterSources.add(mapSqlParameterSource);
        }

        return mapSqlParameterSources;
    }
}
