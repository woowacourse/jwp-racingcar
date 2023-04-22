package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.GameEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class RacingGameDao {

    private static final int FIRST_GAME_INDEX = 0;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RacingGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    public List<GameEntity> findAll() {
        String sqlForGameEntities = "SELECT * FROM RACING_GAME";
        return jdbcTemplate.query(sqlForGameEntities, ObjectMapper.getGameEntityMapper());
    }

    public int saveGame(GameEntity gameResultEntity) {
        String sqlForSaveGame = "INSERT INTO RACING_GAME(count, created_at) VALUES(?, ?)";
        return getIdAfterInsert(sqlForSaveGame, Integer.toString(gameResultEntity.getCount()), gameResultEntity.getCreatedAt().toString());
    }

    public GameEntity getRacingGameById(int gameId) {
        String sqlForGetGame = "SELECT * FROM RACING_GAME WHERE id = ?";
        return jdbcTemplate.query(sqlForGetGame, ObjectMapper.getGameEntityMapper(), gameId).get(FIRST_GAME_INDEX);
    }

}
