package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PlayResultDaoImpl implements PlayResultDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PlayResultDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insertPlayResult(final PlayResult playResult) {
        String sql = "INSERT INTO PLAY_RESULT (winners, trial_count) VALUES(:winners, :trialCount)";

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(playResult);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public List<PlayResult> selectAllResults() {
        String sql = "select id, winners, trial_count from play_result order by id desc";

        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
            int id = rs.getInt("id");
            String winners = rs.getString("winners");
            int trialCount = rs.getInt("trial_count");
            return new PlayResult(id, winners, trialCount);
        });
    }

}
