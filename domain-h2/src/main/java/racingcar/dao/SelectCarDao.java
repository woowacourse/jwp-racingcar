package racingcar.dao;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameId;

public class SelectCarDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CarEntity> actorRowMapper = (resultSet, rowNum) -> new CarEntity(
            resultSet.getInt("car_id"),
            resultSet.getString("name"),
            resultSet.getInt("position"),
            resultSet.getInt("game_id")
    );

    public SelectCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarEntity> findAllByGameId(final List<GameId> gameId) {
        final String inParams = gameId.stream()
                .map(GameId::getValue)
                .map(id -> "?")
                .collect(Collectors.joining(","));

        final String sql = String.format(
                "SELECT car_id, name, game_id, position FROM CAR WHERE game_id IN (%s)", inParams);
        return jdbcTemplate.query(sql, actorRowMapper, gameId.stream()
                .map(GameId::getValue)
                .toArray());
    }

    public List<CarEntity> findAll() {
        final String sql = "SELECT car_id, name,game_id, position FROM CAR";

        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
