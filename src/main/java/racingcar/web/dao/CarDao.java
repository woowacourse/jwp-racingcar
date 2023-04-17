package racingcar.web.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.web.entity.CarEntity;

import java.sql.PreparedStatement;

@Component
public class CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(final CarEntity carEntity){
        String sql = "insert into car (player_name, final_position, is_winner, game_result_id) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, carEntity.getPlayerName());
            ps.setInt(2, carEntity.getFinalPosition());
            ps.setBoolean(3, carEntity.isWinner());
            ps.setLong(4, carEntity.getGameResultId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
