package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;

@Repository
public class JdbcCarDao implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(final CarEntity carEntity) {
        String sql = "INSERT INTO car(name, position, game_id, is_win) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, carEntity.getName(), carEntity.getPosition(), carEntity.getGameId(), carEntity.getIsWin());
    }
}
