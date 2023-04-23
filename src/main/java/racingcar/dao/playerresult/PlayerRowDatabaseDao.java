package racingcar.dao.playerresult;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayerRow;

@Repository
public class PlayerRowDatabaseDao implements PlayerRowDao{

	private final JdbcTemplate jdbcTemplate;

	public PlayerRowDatabaseDao (final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void savePlayerRows (final List<PlayerRow> playerRows, final Long gameId) {
		String sql = "INSERT INTO player_result (game_id, name, position, is_winner) VALUES (?, ?, ?, ?)";

		playerRows.forEach(racingCar -> {
			jdbcTemplate.update(sql,
					gameId,
					racingCar.getName(),
					racingCar.getPosition(),
					racingCar.isWinner());
		});
	}

	@Override
	public List<PlayerRow> fetchAllPlayerRowByGameId (Long gameId) {
		String sql = "SELECT * FROM player_result where game_id = ?";

		return jdbcTemplate.query(sql, (rs, rowNum) ->
						new PlayerRow(rs.getLong("id"), rs.getLong("game_id"), rs.getString("name"), rs.getInt("position"),
								rs.getBoolean("is_winner"))
				, gameId
		);
	}
}
