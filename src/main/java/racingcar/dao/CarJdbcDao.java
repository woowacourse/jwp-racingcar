package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarJdbcDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CarEntity> rowMapper = (rs, rowNum) -> new CarEntity(
            rs.getString("player_name"),
            rs.getInt("final_position"),
            rs.getBoolean("is_winner"),
            rs.getLong("game_result_id")
    );

    public CarJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(List<CarEntity> carEntities) {
        final String sql = "insert into car (player_name, final_position, is_winner, game_result_id) values (?, ?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (CarEntity carEntity : carEntities) {
            batchArgs.add(new Object[]{carEntity.getPlayerName(), carEntity.getFinalPosition(), carEntity.isWinner(), carEntity.getGameResultId()});
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @Override
    public List<CarEntity> findByGameResultId(Long gameResultId) {
        return jdbcTemplate.query("SELECT * FROM CAR WHERE game_result_id = ?", rowMapper, gameResultId);
    }
}
