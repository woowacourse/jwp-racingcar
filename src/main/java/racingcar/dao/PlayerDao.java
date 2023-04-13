package racingcar.dao;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.RaceResultDto;

@Repository
public class PlayerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PlayerDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void insertAll(final RaceResultDto raceResultDto, final int raceId) {
        final List<CarDto> carDtos = raceResultDto.getCarDtos();

        carDtos.forEach(carDto -> insert(carDto, raceId));
    }

    public void insert(final CarDto carDto, final int raceId) {
        final String sql = "INSERT INTO PLAYER ( name, identifier, position, race_id ) values (:name, :identifier, :position, :race_id)";
        final MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("name", carDto.getName())
            .addValue("identifier", carDto.getIdentifier())
            .addValue("position", carDto.getPosition())
            .addValue("race_id", raceId);

        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<Integer> getWinnerCarIds(final int raceId, final RaceResultDto raceResultDto) {
        final List<CarDto> winners = raceResultDto.getWinners();

        return winners.stream()
            .map(winner -> getWinnerCarId(raceId, winner))
            .collect(Collectors.toList());
    }

    public Integer getWinnerCarId(final int raceId, final CarDto carDto) {
        final String sql = "SELECT id FROM PLAYER WHERE name = :name AND identifier = :identifier AND race_id = :race_id";
        final MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("name", carDto.getName())
            .addValue("identifier", carDto.getIdentifier())
            .addValue("race_id", raceId);

        return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
    }
}
