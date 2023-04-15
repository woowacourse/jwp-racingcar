package racingcar.dao;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameInputDto;

@Repository
public class RaceDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RaceDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public long insert(GameInputDto gameInputDto) {
        final String sql = "INSERT INTO RACE(play_count) values (:playCount)";
        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        final MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("playCount", gameInputDto.getCount());
        namedParameterJdbcTemplate.update(sql, params, generatedKeyHolder, new String[]{"id"});

        return Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();
    }
    
    public List<Long> findAllIds() {
        final String sql = "SELECT id FROM RACE";
        return namedParameterJdbcTemplate.queryForList(sql, Collections.emptyMap()).stream()
                .map(stringObjectMap -> stringObjectMap.get("id"))
                .map(String::valueOf)
                .map(Long::parseLong)
                .collect(Collectors.toUnmodifiableList());
    }
}
