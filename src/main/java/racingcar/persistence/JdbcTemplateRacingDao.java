package racingcar.persistence;

import java.sql.PreparedStatement;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.persistence.entity.GameResultEntity;

@Repository
public class JdbcTemplateRacingDao implements RacingDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateRacingDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public GameResultEntity saveGameResult(final RacingGame racingGame, final int trialCount) {
        final String sql = "INSERT INTO GAME_RESULT (winners, trial_count) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, racingGame.getWinners().stream()
                    .map(Car::getCarName)
                    .collect(Collectors.joining(",")));
            preparedStatement.setInt(2, trialCount);
            return preparedStatement;
        }, keyHolder);
        if (keyHolder.getKey() == null) {
            throw new IllegalStateException("게임 결과 저장에 실패했습니다.");
        }
        GameResultEntity gameResultEntity = getGameResultEntityById(keyHolder.getKey().longValue());
        return gameResultEntity;
    }

    private GameResultEntity getGameResultEntityById(long gameResultId) {
        final String sql = "SELECT id, trial_count, winners, created_at "
                + "FROM game_result WHERE id = ?";

        GameResultEntity gameResultEntity = jdbcTemplate.queryForObject(sql,
                (resultSet, rowNumber) -> new GameResultEntity(
                        resultSet.getLong("id"),
                        resultSet.getInt("trial_count"),
                        resultSet.getString("winners"),
                        resultSet.getDate("created_at")),
                gameResultId);
        return gameResultEntity;
    }

    @Transactional
    public void savePlayerResults(final RacingGame racingGame, final long gameResultId) {
        String sqlToInsertPlayerResult = "INSERT INTO PLAYER_RESULT (name, position, game_result_id) values (?, ?, ?)";
        for (Car car : racingGame.getCars()) {
            jdbcTemplate.update(sqlToInsertPlayerResult, car.getCarName(), car.getPosition(), gameResultId);
        }
    }
}
