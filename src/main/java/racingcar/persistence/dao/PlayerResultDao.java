package racingcar.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarData;
import racingcar.persistence.entity.PlayerResultEntity;

import java.util.List;

@Repository
public class PlayerResultDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(final List<CarData> racingCarData, final Long gameResultKey) {
        for (CarData carData : racingCarData) {
            String sql = "INSERT INTO PLAYER_RESULT (name, position, game_result_id) values (?, ?, ?)";
            jdbcTemplate.update(sql, carData.getName(), carData.getPosition(), gameResultKey);
        }
    }

    public List<PlayerResultEntity> selectAll() {
        final String sql = "SELECT * FROM PLAYER_RESULT";

        return jdbcTemplate.query(sql, (resultSet, count) -> new PlayerResultEntity(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("position"),
                resultSet.getInt("game_result_id")
        ));
    }
}
