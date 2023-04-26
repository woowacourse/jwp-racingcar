package racingcar.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.persistence.entity.PlayerResultEntity;

import java.util.List;

@Repository
public class PlayerResultDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(final List<PlayerResultEntity> playerResultEntities) {
        final String sql = "INSERT INTO PLAYER_RESULT (name, position, game_result_id) values (?, ?, ?)";

        for (var entity : playerResultEntities) {
            jdbcTemplate.update(sql, entity.getName(), entity.getPosition(), entity.getGameResultId());
        }
    }

    public List<PlayerResultEntity> selectAll() {
        final String sql = "SELECT * FROM PLAYER_RESULT";

        return jdbcTemplate.query(sql, (resultSet, count) -> PlayerResultEntity.ofOutward(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("position"),
                resultSet.getInt("game_result_id")
        ));
    }
}
