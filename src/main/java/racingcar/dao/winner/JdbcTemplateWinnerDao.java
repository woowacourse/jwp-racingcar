package racingcar.dao.winner;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import racingcar.dto.WinnerDto;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcTemplateWinnerDao implements WinnerDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    public JdbcTemplateWinnerDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    
    @Override
    public void save(final WinnerDto winnerDto) {
        final String sql = "INSERT INTO WINNER(game_id, car_id) values (:game_id, :car_id)";
        
        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("game_id", winnerDto.getGameId())
                .addValue("car_id", winnerDto.getCarId());
        namedParameterJdbcTemplate.update(sql, params);
    }
    
    @Override
    public List<WinnerDto> findWinnerDtosByGameId(long gameId) {
        String sql = "SELECT car_id FROM WINNER WHERE game_id=:game_id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("game_id", gameId);
        
        return namedParameterJdbcTemplate.queryForList(sql, params).stream()
                .map(stringObjectMap -> stringObjectMap.get("car_id"))
                .map(String::valueOf)
                .map(Long::parseLong)
                .map(carId -> new WinnerDto(gameId, carId))
                .collect(Collectors.toUnmodifiableList());
    }
}
