package racingcar.dao.web;

import org.springframework.jdbc.core.RowMapper;
import racingcar.domain.Winner;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

public class ObjectMapper {

    public static RowMapper<CarEntity> getCarEntityMapper() {
        return (resultSet, rowNum) -> new CarEntity.Builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .position(resultSet.getInt("position"))
                .build();
    }

    public static RowMapper<GameEntity> getGameEntityMapper() {
        return (resultSet, rowNum) -> new GameEntity.Builder()
                .id(resultSet.getInt("id"))
                .count(resultSet.getInt("count"))
                .build();
    }

    public static RowMapper<Winner> getWinnersEntityMapper() {
        return (resultSet, rowNum) -> new Winner(
                resultSet.getString("name")
        );
    }

    public static RowMapper<Integer> getCarIdMapper() {
        return (resultSet, rowNum) -> resultSet.getInt("id");
    }

}
