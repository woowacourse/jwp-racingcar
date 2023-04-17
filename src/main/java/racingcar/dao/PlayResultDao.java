package racingcar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PlayResultDao {
    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Long insertWithKeyHolder(int trialCount, List<String> winners) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "insert into PLAY_RESULT (trialcount, winners) values (?, ?)";

        jdbcTemplate.update((Connection con) -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            preparedStatement.setString(2, String.join(",", winners));
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
