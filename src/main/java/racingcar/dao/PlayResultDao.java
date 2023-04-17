package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PlayResultDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PlayResultDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int insertPlayResult(final PlayResult playResult) {
        String sql = "INSERT INTO PLAY_RESULT (winners, trial_count) VALUES(:winners, :trialCount)";

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(playResult);
        return namedParameterJdbcTemplate.update(sql, parameterSource, new GeneratedKeyHolder());
    }

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
