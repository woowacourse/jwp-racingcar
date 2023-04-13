package racingcar.dao;

import java.util.Objects;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RaceDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RaceDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int insert() {
        final String sql = "INSERT INTO RACE() values ()";
        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        final MapSqlParameterSource params = new MapSqlParameterSource();
        namedParameterJdbcTemplate.update(sql, params, generatedKeyHolder, new String[]{"id"});

        return Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();
    }
}
