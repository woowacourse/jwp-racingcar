package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.RecordDto;
import java.util.List;

@Repository
public class RecordDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecordDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final long gameId, final boolean isWinner, final Car car) {
        String sql = "insert into record(game_id, position, is_winner, player_name) values (?, ?, ?, ?)";

        jdbcTemplate.update(sql, gameId, car.getDistance(), isWinner, car.getName());
    }

    public List<RecordDto> findAllByGameId(final long gameId) {
        String sql = "select * from record where game_id = ?";

        return jdbcTemplate.query(sql, recordDtoRowMapper, gameId);
    }

    private final RowMapper<RecordDto> recordDtoRowMapper = (resultSet, rowNum) -> new RecordDto(
            resultSet.getInt("game_id"),
            resultSet.getString("player_name"),
            resultSet.getInt("position"),
            resultSet.getBoolean("is_winner")
    );
}
