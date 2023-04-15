package racingcar.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.RacingResultRequestDto;

@Repository
public class PlayerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PlayerDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void insertAll(final RacingResultRequestDto racingResultRequestDto, final long raceId) {
        final List<CarDto> carDtos = racingResultRequestDto.getCarDtos();

        carDtos.forEach(carDto -> insert(carDto, raceId));
    }

    public void insert(final CarDto carDto, final long raceId) {
        final String sql = "INSERT INTO PLAYER ( name, identifier, position, race_id ) values (:name, :identifier, :position, :race_id)";
        final MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("name", carDto.getName())
            .addValue("identifier", carDto.getIdentifier())
            .addValue("position", carDto.getPosition())
            .addValue("race_id", raceId);

        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<Integer> findWinnerCarIds(final long raceId, final RacingResultRequestDto racingResultRequestDto) {
        final List<CarDto> winners = racingResultRequestDto.getWinners();

        return winners.stream()
            .map(winner -> findWinnerCarId(raceId, winner))
            .collect(Collectors.toList());
    }

    public int findWinnerCarId(final long raceId, final CarDto carDto) {
        final String sql = "SELECT id FROM PLAYER WHERE name = :name AND identifier = :identifier AND race_id = :race_id";
        final MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("name", carDto.getName())
            .addValue("identifier", carDto.getIdentifier())
            .addValue("race_id", raceId);

        return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
    }
    
    public List<CarDto> findByIds(List<Long> playerIds) {
        String sql = "SELECT name, identifier, position FROM PLAYER WHERE id=:id";
        return playerIds.stream()
                .map(playerId -> new MapSqlParameterSource().addValue("id", playerId))
                .map(params -> namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(CarDto.class)))
                .collect(Collectors.toUnmodifiableList());
    }
    
    public List<CarDto> findByRaceIds(Long raceId) {
        String sql = "SELECT name, identifier, position FROM PLAYER WHERE race_id=:raceId";
        SqlParameterSource params = new MapSqlParameterSource().addValue("raceId", raceId);
        return namedParameterJdbcTemplate.queryForList(sql, params).stream()
                .map(stringObjectMap -> {
                    String name = String.valueOf(stringObjectMap.get("name"));
                    String identifier = String.valueOf(stringObjectMap.get("identifier"));
                    String position = String.valueOf(stringObjectMap.get("position"));
                    
                    Car car = new Car(name, Integer.parseInt(identifier));
                    car.drive(Integer.parseInt(position));
                    return new CarDto(car);
                }).collect(Collectors.toUnmodifiableList());
    }
}
