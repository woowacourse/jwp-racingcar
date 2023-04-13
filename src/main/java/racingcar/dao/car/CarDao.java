package racingcar.dao.car;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.car.dto.CarRegisterRequest;

@Repository
public class CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(final CarRegisterRequest carRegisterRequest) {
        final SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("CAR").usingGeneratedKeyColumns("id");

        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", carRegisterRequest.getName())
                .addValue("position", carRegisterRequest.getPosition())
                .addValue("race_result_id", carRegisterRequest.getPlayResultId());

        jdbcInsert.execute(params);
    }
}
