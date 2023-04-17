package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultDto;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class GameResultDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameResultDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<GameResultEntity> rowMapper = (resultSet, rowNum) -> GameResultEntity.of(
            resultSet.getInt("id"),
            resultSet.getInt("trial_count"),
            resultSet.getTime("created_at").toLocalTime()
    );

    public int save(GameResultDto resultDto) {
        String sql = "insert into GAME_RESULT (trial_count) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, resultDto.getTrialCount());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public List<GameResultEntity> findAll() {
        String sql = "select * from GAME_RESULT";

        return jdbcTemplate.query(sql, rowMapper);
    }
}
