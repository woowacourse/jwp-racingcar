package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.PlayerEntity;

import java.util.List;

@Repository
public class PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void batchInsert(final List<PlayerEntity> playerEntities) {
        String sql = "insert into PLAYER (name, position, game_id) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, playerEntities, playerEntities.size(), (ps, argument) -> {
            ps.setString(1, argument.getName());
            ps.setInt(2, argument.getPosition());
            ps.setLong(3, argument.getGameId());
        });
    }

}
