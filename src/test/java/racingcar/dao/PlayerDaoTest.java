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
import racingcar.dao.entity.PlayerEntity;

@JdbcTest
class PlayerDaoTest {

	private PlayerDao playerDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<PlayerEntity> rowMapper = (resultSet, rowNum) -> new PlayerEntity(
		resultSet.getLong("id"),
		resultSet.getLong("game_id"),
		resultSet.getString("name"),
		resultSet.getInt("position")
	);

	@BeforeEach
	void setUp() {
		playerDao = new PlayerDao(jdbcTemplate);
	}

	@Test
	void batchInsertTest() {
		//given
		final GameEntity gameEntity = new GameEntity(null, "준팍", 10, null);
		final GameDao gameDao = new GameDao(jdbcTemplate);
		final long gameId = gameDao.save(gameEntity);

		final PlayerEntity junpakEntity = new PlayerEntity(null, gameId, "준팍", 7);
		final PlayerEntity moominEntity = new PlayerEntity(null, gameId, "무민", 1);

		//when
		playerDao.batchInsert(List.of(junpakEntity, moominEntity));
		final List<PlayerEntity> playerEntities = jdbcTemplate.query("SELECT * FROM PLAYER", rowMapper);

		//then
		assertThat(playerEntities).hasSize(2);
	}
}
