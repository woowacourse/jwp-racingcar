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

    public void insert(Long play_result_id, String playerName, int playerPosition) {
        final String sql = "insert into RACING_CAR (player_name, player_position, play_result_id) values (?,?,?)";
        jdbcTemplate.update(sql, playerName, playerPosition, play_result_id);
    }

    public List<RacingCarDto> select(int id) {
        String sql = "SELECT * FROM RACING_CAR WHERE play_result_id = " + id;
        RowMapper<RacingCarDto> racingCar = (rs, rowNum)
                -> new RacingCarDto(rs.getString("player_name"), rs.getInt("player_position"));
        return jdbcTemplate.query(sql, racingCar);
    }
}
