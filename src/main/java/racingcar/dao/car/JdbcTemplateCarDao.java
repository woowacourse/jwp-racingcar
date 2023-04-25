package racingcar.dao.car;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.WinnerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcTemplateCarDao implements CarDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    public JdbcTemplateCarDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    
    @Override
    public void save(final CarDto carDto) {
        final String sql = "INSERT INTO CAR (name, position, game_id) values (:name, :position, :game_id)";
        final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", carDto.getName())
                .addValue("position", carDto.getPosition())
                .addValue("game_id", carDto.getGameId());
        
        namedParameterJdbcTemplate.update(sql, params, generatedKeyHolder, new String[]{"id"});
    }
    
    @Override
    public long findIdByGameIdAndName(final long gameId, final String name) {
        final String sql = "SELECT id FROM CAR WHERE game_id=:gameId and name=:name";
        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("gameId", gameId)
                .addValue("name", name);
        
        return namedParameterJdbcTemplate.queryForObject(sql, params, long.class);
    }
    
    @Override
    public List<CarDto> findCarDtosByCarIds(final List<Long> carIds) {
        final String sql = "SELECT game_id, name, position FROM CAR WHERE id=:carId";
        final List<CarDto> carDtos = new ArrayList<>();
        
        for (long carId : carIds) {
            final SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("carId", carId);
            
            final CarDto carDto = namedParameterJdbcTemplate.queryForObject(sql, params, (rs, rowNum)
                    -> new CarDto(rs.getLong("game_id"), rs.getString("name"), rs.getInt("position")));
            carDtos.add(carDto);
        }
        return carDtos;
    }
    
    @Override
    public List<CarDto> findCarDtosByGameId(final long gameId) {
        final String sql = "SELECT game_id, name, position FROM CAR WHERE game_id=:gameId";
        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("gameId", gameId);
        
        return namedParameterJdbcTemplate.queryForList(sql, params).stream()
                .map(stringObjectMap -> {
                    final String name = String.valueOf(stringObjectMap.get("name"));
                    final int position = Integer.parseInt(String.valueOf(stringObjectMap.get("position")));
                    return new CarDto(gameId, name, position);
                })
                .collect(Collectors.toUnmodifiableList());
    }
}
