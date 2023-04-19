package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;

import java.util.List;

@Repository
public class CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final String name, final int position, final long playResultId) {
        String sql = "insert into CAR (name, position, game_id) values (?, ?, ?)";
        jdbcTemplate.update(sql, name, position, playResultId);
    }

    public List<CarEntity> findRacingCarByGameId(final long gameId) {
        final String sql = "SELECT name, position FROM CAR where game_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            final String name = rs.getString("name");
            final int position = rs.getInt("position");

            return new CarEntity(name, position);
        }, gameId);
    }
}
