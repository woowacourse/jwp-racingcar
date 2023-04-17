package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameResultDto;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RacingHistoryDao {
    private final SimpleJdbcInsert insertRacingGame;
    private final JdbcTemplate jdbcTemplate;

    public RacingHistoryDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        this.insertRacingGame = new SimpleJdbcInsert(dataSource)
                .withTableName("play_result")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<RacingGameResultDto> racingGameResultDtoRowMapper = (resultSet, rowNum) -> {
        RacingGameResultDto racingGameResultDto = new RacingGameResultDto(
                resultSet.getString("winners"),
                resultSet.getInt("play_count"),
                makeRacingCarDto(resultSet, rowNum)
        );
        return racingGameResultDto;
    };

    private List<RacingCarDto> makeRacingCarDto(final ResultSet resultSet, final Integer rowNum) throws SQLException {
        PlayersHistoryDao playersHistoryDao = new PlayersHistoryDao(insertRacingGame.getJdbcTemplate().getDataSource(), jdbcTemplate);
        return playersHistoryDao.getResult(resultSet.getLong("id"));
    }

    public int insertResult(final RacingGameResultDto racingGameResultDto) {
        final Map<String, Object> params = new HashMap<>();
        params.put("winners", racingGameResultDto.getWinners());
        params.put("play_count", racingGameResultDto.getPlayCount());
        params.put("created_at", LocalDateTime.now());
        return insertRacingGame.executeAndReturnKey(params).intValue();
    }

    public List<RacingGameResultDto> getResult() {
        String sql = "select id, winners, play_count from play_result";
        return jdbcTemplate.query(sql, racingGameResultDtoRowMapper);
    }
}
