package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.RacingCarEntity;
import racingcar.entity.RacingGameEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class RacingCarDao {
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<RacingCarEntity> carRowMapper = (resultSet, rowNum) ->
            new RacingCarEntity.Builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .position(resultSet.getInt("position"))
                    .build();

    private final RowMapper<RacingGameEntity> rowMapper = (resultSet, rowNum) -> {
        int gameId = resultSet.getInt("id");
        String sql = "SELECT id, name, position FROM RACING_CAR WHERE racing_game_id = ?";
        List<RacingCarEntity> racingCarEntities = jdbcTemplate.query(sql, carRowMapper, gameId);
        return new RacingGameEntity.Builder()
                .id(gameId)
                .racingCars(racingCarEntities)
                .count(resultSet.getInt("count"))
                .winners(resultSet.getString("winners"))
                .build();
    };

    @Autowired
    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RacingGameEntity save(RacingGameEntity racingGameEntity) {
        String sql = "INSERT INTO RACING_GAME(count, winners, created_at) VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, Integer.toString(racingGameEntity.getCount()));
            preparedStatement.setString(2, racingGameEntity.getWinners());
            preparedStatement.setString(3, racingGameEntity.getCreatedAt().toString());
            return preparedStatement;
        }, keyHolder);
        racingGameEntity.setId((int) keyHolder.getKey());
        racingGameEntity.getRacingCars().stream()
                .forEach(racingCarEntity -> asdfg(racingGameEntity.getId(), racingCarEntity));
        return racingGameEntity;
    }

    public void asdfg(int gameId, RacingCarEntity racingCarEntity) {
        String sqlForRacingGameEntity = "INSERT INTO RACING_CAR(position, name, racing_game_id) VALUES(?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlForRacingGameEntity, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, Integer.toString(racingCarEntity.getPosition()));
            preparedStatement.setString(2, racingCarEntity.getName());
            preparedStatement.setString(3, Integer.toString(gameId));
            return preparedStatement;
        }, keyHolder);

        racingCarEntity.setId((int) keyHolder.getKey());
    }

    public List<RacingGameEntity> findAll() {
        String sql = "SELECT id, winners, count FROM RACING_GAME";
        return jdbcTemplate.query(sql, rowMapper);
    }

}
