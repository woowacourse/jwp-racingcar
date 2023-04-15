package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dto.GameResultDto;

import java.sql.PreparedStatement;

@Component
public class GameResultJdbcDAO implements GameResultDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameResultJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(GameResultDto resultDto) {
        String sql = "insert into GAME_RESULT (winners, trial_count) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"game_id"});
            preparedStatement.setString(1, resultDto.getWinners());
            preparedStatement.setInt(2, resultDto.getTrialCount());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
