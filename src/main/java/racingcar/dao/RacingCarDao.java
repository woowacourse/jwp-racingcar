package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class RacingCarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GameEntity saveGame(GameEntity gameEntity) {
        String sqlForGameEntity = "INSERT INTO GAME(count, winners, created_at) VALUES(?, ?, ?)";
        gameEntity.setId(getIdAfterInsert(
                sqlForGameEntity,
                Integer.toString(gameEntity.getCount()),
                gameEntity.getWinners(),
                gameEntity.getCreatedAt().toString())
        );

        return gameEntity;
    }

    public void saveCar(CarEntity carEntity) {
        String sqlForCarEntity = "INSERT INTO CAR(position, name, game_id) VALUES(?, ?, ?)";
        carEntity.setId(getIdAfterInsert(
                sqlForCarEntity,
                Integer.toString(carEntity.getPosition()),
                carEntity.getName(),
                Integer.toString(carEntity.getGameId()))
        );
    }

    private int getIdAfterInsert(String sqlForRacingGameEntity, String... sqlParameters) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlForRacingGameEntity, Statement.RETURN_GENERATED_KEYS);
            setSqlParameter(preparedStatement, sqlParameters);
            return preparedStatement;
        }, keyHolder);
        return (int) keyHolder.getKey();
    }

    private static void setSqlParameter(PreparedStatement preparedStatement, String... sqlParameters) throws SQLException {
        for (int parameterIndex = 1; parameterIndex <= sqlParameters.length; parameterIndex++) {
            preparedStatement.setString(parameterIndex, sqlParameters[parameterIndex - 1]);
        }
    }

    public List<GameEntity> findAllGame() {
        String sqlForGameEntities = "SELECT * FROM GAME";
        return jdbcTemplate.query(sqlForGameEntities, ObjectMapper.getGameEntityMapper());
    }

    public List<CarEntity> findCarsByGameId(int gameId) {
        String sqlForCarEntities = "SELECT * FROM CAR WHERE game_id = ?";
        return jdbcTemplate.query(sqlForCarEntities, ObjectMapper.getCarEntityMapper(), gameId);
    }

}
