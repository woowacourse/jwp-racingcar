package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

@Repository
public class GameDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(final int trialCount) {
        final String sql = "INSERT INTO GAME(trial_count) VALUES(?) ";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[] {"id"});
            preparedStatement.setInt(1, trialCount);
            return preparedStatement;
        }, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }
}
