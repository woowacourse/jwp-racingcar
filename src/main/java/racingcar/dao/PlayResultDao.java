package racingcar.dao;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.service.PlayResult;

@Repository
public class PlayResultDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertPlayResult(final PlayResult playResult) {
        String sql = "INSERT INTO PLAY_RESULT (winners, trial_count) VALUES(?,?)";

        return jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, playResult.getWinners());
            preparedStatement.setInt(2, playResult.getTrialCount());
            return preparedStatement;
        }, new GeneratedKeyHolder());
    }

    public List<PlayResult> selectAllResults() {
        String sql = "select id, winners, trial_count from play_result order by id desc";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int id = rs.getInt("id");
            String winners = rs.getString("winners");
            int trialCount = rs.getInt("trial_count");
            return new PlayResult(id, winners, trialCount);
        });
    }
}
