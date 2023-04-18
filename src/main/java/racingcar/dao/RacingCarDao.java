package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarDto;

@Repository
public class RacingCarDao {
    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Long gameId, String playerName, int playerPosition) {
        final String sql = "INSERT INTO racing_car (player_name, player_position, game_id) VALUES (?,?,?)";
        jdbcTemplate.update(sql, playerName, playerPosition, gameId);
    }

    public List<RacingCarDto> select(int id) {
        String sql = "SELECT * FROM racing_car WHERE game_id = " + id;
        RowMapper<RacingCarDto> racingCar = (rs, rowNum)
                -> new RacingCarDto(rs.getString("player_name"), rs.getInt("player_position"));
        return jdbcTemplate.query(sql, racingCar);
    }
}
