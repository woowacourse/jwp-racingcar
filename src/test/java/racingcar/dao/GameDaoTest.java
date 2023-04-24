package racingcar.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import racingcar.dao.entity.GameEntity;
import racingcar.dto.GameResultDto;
import racingcar.dao.entity.PlayerEntity;

@JdbcTest
class GameDaoTest {

	private GameDao gameDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<GameEntity> rowMapper = (resultSet, rowNum) -> new GameEntity(
		resultSet.getLong("id"),
		resultSet.getString("winners"),
		resultSet.getInt("trial_count"),
		resultSet.getTimestamp("created_at").toLocalDateTime()
	);

	@BeforeEach
	void setUp() {
		gameDao = new GameDao(jdbcTemplate);
	}

	@Test
	void saveTest() {
		// given
		final GameEntity gameEntity = new GameEntity(null, "준팍", 10, null);

		// when
		gameDao.save(gameEntity);
		final List<GameEntity> gameEntities = jdbcTemplate.query("select * from game", rowMapper);

		//then
		assertThat(gameEntities).hasSize(1);
	}

	@Test
	void findGamePlayHistoryAllTest() {
		//given
		final GameEntity gameEntity = new GameEntity(null, "준팍", 10, null);
		final long gameId = gameDao.save(gameEntity);

		final PlayerEntity junpakEntity = new PlayerEntity(null, gameId, "준팍", 7);
		final PlayerEntity moominEntity = new PlayerEntity(null, gameId, "무민", 1);
		final PlayerDao playerDao = new PlayerDao(jdbcTemplate);
		playerDao.batchInsert(List.of(junpakEntity, moominEntity));

		//when
		final List<GameResultDto> gamePlayHistoryAll = gameDao.findGamePlayHistoryAll();

		//then
		assertThat(gamePlayHistoryAll).hasSize(2);
	}

}
