package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.RaceResultEntity;

@Repository
public class RaceResultDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public RaceResultDao(final JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName("RACE_RESULT")
                        .usingGeneratedKeyColumns("id");
    }

    public Long save(final RaceResultEntity raceResultEntity) {
        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(raceResultEntity))
                               .longValue();
    }
}
