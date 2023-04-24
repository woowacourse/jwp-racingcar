package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Winner;

import java.util.List;

@Repository
public class JdbcWinnerDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcWinnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winner")
                .usingGeneratedKeyColumns("id");
    }

    public void saveAll(List<Winner> winners) {
        SqlParameterSource[] sources = SqlParameterSourceUtils.createBatch(winners);
        simpleJdbcInsert.executeBatch(sources);
    }

    public List<String> findNamesByGameResultId(long gameResultId) {
        String sql = "SELECT name FROM winner WHERE game_result_id = ?";
        return jdbcTemplate.query(sql, (rs, rn) -> rs.getString("name"), gameResultId);
    }
}
