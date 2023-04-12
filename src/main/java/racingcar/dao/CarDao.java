package racingcar.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;

@Repository
public class CarDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CarDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(RacingCarResultDto racingCarResultDto) {
        String sql = "INSERT INTO car (name, position, is_win, game_id) VALUES (:name, :position, :isWin, :gameId)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(racingCarResultDto);
        jdbcTemplate.update(sql, namedParameters);
    }

    public List<String> findWinnersById(long gameId) {
        String sql = "SELECT name FROM car WHERE game_id = :game_id AND is_win = 1";
        Map<String, Long> parameter = Collections.singletonMap("game_id", gameId);
        return jdbcTemplate.query(sql, parameter, (resultSet, count) -> resultSet.getString("name"));
    }

    public List<RacingCarDto> findCarsById(long gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = :game_id";
        Map<String, Long> parameter = Collections.singletonMap("game_id", gameId);
        return jdbcTemplate.query(sql, parameter, (resultSet, count) ->
        {
            String name = resultSet.getString("name");
            int position = resultSet.getInt("position");
            return RacingCarDto.of(name, position);
        });
    }
}
