package racing.dao.car;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import racing.domain.Car;
import racing.dto.CarDto;

@Repository
public class H2CarDao implements CarDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public H2CarDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void insert(final Car car, final int gameId) {
        final String sql = "INSERT INTO CAR(name, position, is_winner, game_id)"
            + "VALUES (:name, :position, :isWinner, :gameId)";
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", car.getName());
        params.addValue("position", car.getPosition());
        params.addValue("isWinner", car.isWinner());
        params.addValue("gameId", gameId);
        namedParameterJdbcTemplate.update(sql, params);
    }

    private RowMapper<CarDto> carDtoRowMapper() {
        return (rs, rowNum) -> {
            final String name = rs.getString("name");
            final int position = rs.getInt("position");
            final boolean is_winner = rs.getBoolean("is_winner");
            return new CarDto(name, position, is_winner);
        };
    }

    @Override
    public List<CarDto> findByGameId(final Integer gameId) {
        final String sql = "SELECT * FROM CAR WHERE game_id = :gameId";
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("gameId", gameId);
        return namedParameterJdbcTemplate.query(sql, params, carDtoRowMapper());
    }
}
