package racingcar.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameIdDto;

@Repository
public class GameJdbcDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertActor;

    public GameJdbcDao(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<GameIdDto> gameDtoRowMapper = (resultSet, rowNum) -> GameIdDto.from(
            resultSet.getInt("id")
    );

    @Override
    public int insertGame(final int tryTimes) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("trial_count", tryTimes);
        parameters.put("created_at", Timestamp.valueOf(LocalDateTime.now()));

        return insertActor.executeAndReturnKey(parameters).intValue();
    }

    @Override
    public List<GameIdDto> findAll() {
        String sql = "SELECT id FROM game;";

        return jdbcTemplate.query(sql, gameDtoRowMapper);
    }
}
