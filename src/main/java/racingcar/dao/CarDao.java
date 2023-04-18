package racingcar.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;

@Repository
public class CarDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CarDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(List<RacingCarResultDto> racingCarResultDtos) {
        String sql = "INSERT INTO car (name, position, is_win, game_id) VALUES (:name, :position, :isWin, :gameId)";
        jdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(racingCarResultDtos));
    }

    public List<String> findWinnerNamesByGameId(long gameId) {
        String sql = "SELECT name FROM car WHERE game_id = :game_id AND is_win = 1";
        Map<String, Long> parameter = Collections.singletonMap("game_id", gameId);
        return jdbcTemplate.query(sql, parameter, (resultSet, count) -> resultSet.getString("name"));
    }

    public List<RacingCarDto> findCarsByGameId(long gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = :game_id";
        Map<String, Long> parameter = Collections.singletonMap("game_id", gameId);
        return jdbcTemplate.query(sql, parameter, (resultSet, count) ->
        {
            String name = resultSet.getString("name");
            int position = resultSet.getInt("position");
            return RacingCarDto.of(name, position);
        });
    }

    public List<RacingCarResultDto> findAllResults() {
        String sql = "SELECT name, position, is_win, game_id FROM car";
        return jdbcTemplate.query(sql, (resultSet, count) -> new RacingCarResultDto(
                resultSet.getString("name"),
                resultSet.getInt("position"),
                resultSet.getBoolean("is_win"),
                resultSet.getInt("game_Id")));
    }
}
