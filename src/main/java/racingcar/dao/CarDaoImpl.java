package racingcar.dao;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;

@Repository
public class CarDaoImpl implements CarDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void insertCar(final CarDto carDto, final int gameId) {
        String sql = "INSERT INTO CAR(game_id, name, position) VALUES(:gameId, :name, :position)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("gameId", gameId);
        mapSqlParameterSource.addValue("name", carDto.getName());
        mapSqlParameterSource.addValue("position", carDto.getPosition());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource, keyHolder);
    }

    @Override
    public List<CarDto> findCarsByRacingGameId(final int gameId) {
        String sql = "select * from Car where game_id = :gameId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("gameId", gameId);

        List<CarEntity> carEntities = namedParameterJdbcTemplate.query(sql, parameterSource, (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int position = rs.getInt("position");
            return new CarEntity(id, name, position);
        });

        return carEntities.stream()
                .map(entity -> CarDto.of(entity.getName(), entity.getPosition()))
                .collect(Collectors.toList());
    }

}
