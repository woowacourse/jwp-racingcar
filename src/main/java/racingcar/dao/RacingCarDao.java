package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.util.JdbcInsert;
import racingcar.entity.CarEntity;

import java.util.List;

@Repository
public class RacingCarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarEntity> findByGameId(int gameId) {
        String sqlForCarEntities = "SELECT * FROM CAR WHERE game_id = ?";
        return jdbcTemplate.query(sqlForCarEntities, ObjectMapper.getCarEntityMapper(), gameId);
    }

    public int save(CarEntity carEntity) {
        String sqlForCarEntity = "INSERT INTO CAR(position, name, game_id) VALUES(?, ?, ?)";

        return JdbcInsert.getIdAfterInsert(
                jdbcTemplate,
                sqlForCarEntity,
                Integer.toString(carEntity.getPosition()),
                carEntity.getName(),
                Integer.toString(carEntity.getGameId())
        );
    }

}
