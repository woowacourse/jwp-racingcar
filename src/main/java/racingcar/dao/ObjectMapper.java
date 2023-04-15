package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

import java.util.List;

public class ObjectMapper {

    private static RowMapper<CarEntity> getCarEntityMapper() {
        return (resultSet, rowNum) -> new CarEntity.Builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .position(resultSet.getInt("position"))
                        .build();
    }

    public static RowMapper<GameEntity> getGameEntityMapper(JdbcTemplate jdbcTemplate) {
        return (resultSet, rowNum) -> {
            int gameId = resultSet.getInt("id");
            String sql = "SELECT * FROM RACING_CAR WHERE racing_game_id = ?";
            List<CarEntity> carEntities = jdbcTemplate.query(sql, ObjectMapper.getCarEntityMapper(), gameId);
            return new GameEntity.Builder()
                    .id(gameId)
                    .racingCars(carEntities)
                    .count(resultSet.getInt("count"))
                    .winners(resultSet.getString("winners"))
                    .build();
        };
    }

}
