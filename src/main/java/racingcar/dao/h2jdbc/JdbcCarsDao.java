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
    public int insert(final int gameId, final CarDto carInfo, final boolean isWin) {
        final MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("game_id", gameId)
                .addValue("name", carInfo.getName())
                .addValue("position", carInfo.getPosition())
                .addValue("is_win", isWin);

        return simpleJdbcInsert.executeAndReturnKey(mapSqlParameterSource).intValue();
    }

    @Override
    public List<CarDto> findAllByGameId(final int gameId) {
        final String sql = "SELECT name, position FROM cars WHERE game_id = ?";

        return jdbcTemplate.query(sql, carDtoRowMapper, gameId);
    }

    @Override
    public List<String> findWinnerNamesByGameId(final int gameId) {
        final String sql = "SELECT name FROM cars WHERE is_win = TRUE AND game_id = ?";

        return jdbcTemplate.queryForList(sql, String.class, gameId);
    }
}
