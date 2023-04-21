package racingcar.dao.game;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class JdbcTemplateGameDao implements GameDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    public JdbcTemplateGameDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    
    @Override
    public long save(GameDto gameInputDto) {
        final String sql = "INSERT INTO GAME(count) values (:count)";
        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("count", gameInputDto.getCount());
        namedParameterJdbcTemplate.update(sql, params, generatedKeyHolder, new String[]{"id"});
        
        return Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();
    }
    
    @Override
    public List<Long> findAllId() {
        final String sql = "SELECT id FROM GAME";
        return namedParameterJdbcTemplate.queryForList(sql, Collections.emptyMap()).stream()
                .map(stringObjectMap -> stringObjectMap.get("id"))
                .map(String::valueOf)
                .map(Long::parseLong)
                .collect(Collectors.toUnmodifiableList());
    }
}
