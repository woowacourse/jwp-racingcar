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

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GameEntity saveGame(GameEntity gameEntity) {
        String sqlForGameEntity = "INSERT INTO RACING_GAME(count, winners, created_at) VALUES(?, ?, ?)";
        gameEntity.setId(getIdAfterInsert(
                sqlForGameEntity,
                Integer.toString(gameEntity.getCount()),
                gameEntity.getWinners(),
                gameEntity.getCreatedAt().toString())
        );
        gameEntity.getRacingCars()
                .forEach(carEntity -> saveCar(gameEntity.getId(), carEntity));
        return gameEntity;
    }

    public void saveCar(int gameId, CarEntity carEntity) {
        String sqlForCarEntity = "INSERT INTO RACING_CAR(position, name, racing_game_id) VALUES(?, ?, ?)";
        carEntity.setId(getIdAfterInsert(
                sqlForCarEntity,
                Integer.toString(carEntity.getPosition()),
                carEntity.getName(),
                Integer.toString(gameId))
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
        for (int parameterIndex = 1; parameterIndex < sqlParameters.length; parameterIndex++) {
            preparedStatement.setString(parameterIndex, sqlParameters[parameterIndex]);
        }
    }

    public List<GameEntity> findAll() {
        String sqlForGameEntities = "SELECT * FROM RACING_GAME";
        return jdbcTemplate.query(sqlForGameEntities, ObjectMapper.getGameEntityMapper(jdbcTemplate));
    }

}
