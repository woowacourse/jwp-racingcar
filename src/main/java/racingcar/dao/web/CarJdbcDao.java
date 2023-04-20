package racingcar.dao.web;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.entity.CarEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarJdbcDao implements CarDao {
    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public CarJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("car")
                .usingGeneratedKeyColumns("car_id");
    }

    public List<Integer> saveAll(List<CarEntity> carEntities) {
        SqlParameterSource[] sqlParameterSource = SqlParameterSourceUtils.createBatch(carEntities);
        return Arrays.stream(insertActor.executeBatch(sqlParameterSource))
                .mapToObj(Integer::new)
                .collect(Collectors.toList());
    }

    public List<CarEntity> findCarsByGameID(final int gameId) {
        String sql = "SELECT * FROM CAR WHERE game_id =?";
        return jdbcTemplate.query(sql,(resultSet, rowNum)-> {
            int carId = resultSet.getInt("car_id");
            String name = resultSet.getString("name");
            int position = resultSet.getInt("position");
            int game_id = resultSet.getInt("game_id");                    
            return new CarEntity(carId, name, position, game_id);
        }, gameId);
    }
}
