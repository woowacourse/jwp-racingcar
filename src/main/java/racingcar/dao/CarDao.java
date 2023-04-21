package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import racingcar.entity.RacingCarEntity;

@Repository
public class CarDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CarDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(List<RacingCarEntity> racingRacingCarEntities) {
        String sql = "INSERT INTO car (name, position, is_win, game_id) VALUES (:name, :position, :isWin, :gameId)";
        jdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(racingRacingCarEntities));
    }

    public List<RacingCarEntity> findAll() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, (resultSet, count) -> new RacingCarEntity(
                resultSet.getString("name"),
                resultSet.getInt("position"),
                resultSet.getBoolean("is_win"),
                resultSet.getInt("game_Id")));
    }
}
