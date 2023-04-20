package racingcar.dao.h2jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarsDao;
import racingcar.dto.CarDto;

import java.util.List;

@Repository
public class JdbcCarsDao implements CarsDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public JdbcCarsDao(final JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("cars")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<CarDto> carDtoRowMapper = ((rs, rowNum) ->
            new CarDto(rs.getString("name"), rs.getInt("position"))
    );

    @Override
    public int insert(final int gameId, String name, final int position) {
        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("game_id", gameId)
                .addValue("name", name)
                .addValue("position", position);

        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }

    @Override
    public CarDto findById(final int id) {
        final String sql = "SELECT name, position FROM cars WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, carDtoRowMapper, id);
    }

    @Override
    public List<CarDto> findAllByGameId(final int gameId) {
        final String sql = "SELECT name, position FROM cars WHERE game_id = ?";

        return jdbcTemplate.query(sql, carDtoRowMapper, gameId);
    }
}
