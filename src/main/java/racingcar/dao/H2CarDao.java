package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;

import java.util.List;

@Repository
public class H2CarDao implements CarDao {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<CarEntity> carRowMapper = (resultSet, rowNum) -> new CarEntity(
            resultSet.getInt("id"),
            resultSet.getInt("game_id"),
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    public H2CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(List<CarEntity> carEntities) {
        String sql = "INSERT INTO CAR(game_id, name, position) VALUES (?,?,?)";
        for (CarEntity carEntity : carEntities) {
            this.jdbcTemplate.update(sql, carEntity.getGameId(), carEntity.getName(), carEntity.getPosition());
        }
    }

    @Override
    public List<CarEntity> findEndedCars() {
        String sql = "SELECT * FROM CAR";
        return this.jdbcTemplate.query(sql, carRowMapper);
    }
}
