package racingcar.dao;

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
import racingcar.entity.PlayerResult;

@Repository
public class GameResultDao {

	private final JdbcTemplate jdbcTemplate;

	public GameResultDao (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void saveGame (final List<PlayerResult> playerResults, final TryCount tryCount) {
		int gameId = saveGameHistory(tryCount);
		savePlayersStatus(playerResults, gameId);
	}

	public int saveGameHistory (final TryCount tryCount) {
		String sql = "INSERT INTO game_result (trial_count, date_time) VALUES (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, tryCount.getTryCount());
			preparedStatement.setTimestamp(2, Timestamp.from(Instant.now()));
			return preparedStatement;
		}, keyHolder);

		return Objects.requireNonNull(keyHolder.getKey()).intValue();
	}

	private void savePlayersStatus (final List<PlayerResult> playerResults, final int gameId) {
		String sql = "INSERT INTO player_result (game_id, name, position, is_winner) VALUES (?, ?, ?, ?)";

		playerResults.forEach(racingCar -> {
					jdbcTemplate.update(sql,
							gameId,
							racingCar.getName(),
							racingCar.getPosition(),
							racingCar.isWinner());
				});
	}
}
