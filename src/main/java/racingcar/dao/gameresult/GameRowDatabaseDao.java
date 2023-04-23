package racingcar.dao.gameresult;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.TryCount;
import racingcar.entity.GameRow;

@Repository
public class GameRowDatabaseDao implements GameRowDao {

	private final JdbcTemplate jdbcTemplate;

	public GameRowDatabaseDao (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Long saveGameRow (final TryCount tryCount) {
		String sql = "INSERT INTO game_result (trial_count, date_time) VALUES (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, tryCount.getTryCount());
			preparedStatement.setTimestamp(2, Timestamp.from(Instant.now()));
			return preparedStatement;
		}, keyHolder);

		return Objects.requireNonNull(keyHolder.getKey()).longValue();
	}



	public List<GameRow> fetchAllGameRow () {
		String sql = "SELECT * FROM game_result";

		return jdbcTemplate.query(sql, (rs, rowNum) ->
				new GameRow(rs.getLong("id"), rs.getInt("trial_count"),
						rs.getTimestamp("date_time").toLocalDateTime())
		);
	}
}
