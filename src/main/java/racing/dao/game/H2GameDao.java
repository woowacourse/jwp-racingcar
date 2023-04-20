package racing.dao.game;

import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racing.domain.Game;

@Repository
public class H2GameDao implements GameDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public H2GameDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insert(final Game game) {
        final String sql = "INSERT INTO GAME(play_count) values (:playCount)";
        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        final MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("playCount", game.getTotalCount());
        namedParameterJdbcTemplate.update(sql, params, generatedKeyHolder, new String[]{"id"});
        return Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();
    }


    @Override
    public List<Integer> getAllGameId() {
        final String sql = "SELECT id FROM GAME";
        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"));
    }
}
