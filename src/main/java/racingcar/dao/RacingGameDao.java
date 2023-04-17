package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.util.JdbcInsert;
import racingcar.entity.GameEntity;

import java.util.List;

@Repository
public class RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RacingGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(GameEntity gameEntity) {
        String sqlForGameEntity = "INSERT INTO GAME(count, winners, created_at) VALUES(?, ?, ?)";

        return JdbcInsert.getIdAfterInsert(
                jdbcTemplate,
                sqlForGameEntity,
                Integer.toString(gameEntity.getCount()),
                gameEntity.getWinners(),
                gameEntity.getCreatedAt().toString()
        );
    }

    public List<GameEntity> findAll() {
        String sqlForGameEntities = "SELECT * FROM GAME";
        return jdbcTemplate.query(sqlForGameEntities, ObjectMapper.getGameEntityMapper());
    }

}
