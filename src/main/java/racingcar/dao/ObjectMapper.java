package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

import java.util.List;

public class ObjectMapper {

    public static RowMapper<CarEntity> getCarEntityMapper() {
        return (resultSet, rowNum) -> new CarEntity.Builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .position(resultSet.getInt("position"))
                        .gameId(resultSet.getInt("game_id"))
                        .build();
    }

    public static RowMapper<GameEntity> getGameEntityMapper() {
        return (resultSet, rowNum) -> new GameEntity.Builder()
                    .id(resultSet.getInt("id"))
                    .count(resultSet.getInt("count"))
                    .winners(resultSet.getString("winners"))
                    .build();

    }

}
