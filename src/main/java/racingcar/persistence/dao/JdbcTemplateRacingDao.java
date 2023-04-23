package racingcar.persistence.dao;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.persistence.entity.GameResultEntity;
import racingcar.persistence.entity.PlayerResultEntity;

@Repository
public class JdbcTemplateRacingDao implements RacingDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateRacingDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GameResultEntity saveGameResult(final GameResultEntity gameResultEntityToSave) {
        final String sql = "INSERT INTO GAME_RESULT (winners, trial_count) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, gameResultEntityToSave.getWinners());
            preparedStatement.setInt(2, gameResultEntityToSave.getTrialCount());
            return preparedStatement;
        }, keyHolder);
        if (keyHolder.getKey() == null) {
            throw new IllegalStateException("게임 결과 저장에 실패했습니다.");
        }
        return getGameResultById(keyHolder.getKey().longValue());
    }

    private GameResultEntity getGameResultById(long gameResultId) {
        final String sql = "SELECT id, trial_count, winners, created_at "
                + "FROM game_result WHERE id = ?";

        GameResultEntity gameResultEntity = this.jdbcTemplate.queryForObject(sql,
                (resultSet, rowNumber) -> new GameResultEntity(
                        resultSet.getLong("id"),
                        resultSet.getInt("trial_count"),
                        resultSet.getString("winners"),
                        resultSet.getDate("created_at")),
                gameResultId);
        return gameResultEntity;
    }

    public void savePlayerResults(final List<PlayerResultEntity> playerResultEntities) {
        String sql = "INSERT INTO PLAYER_RESULT (name, position, game_result_id) values (?, ?, ?)";
        for (PlayerResultEntity playerResultEntity : playerResultEntities) {
            this.jdbcTemplate.update(sql,
                    playerResultEntity.getName(),
                    playerResultEntity.getPosition(),
                    playerResultEntity.getGameResultId());
        }
    }

    public List<GameResultEntity> getAllGameResults() {
        String sql = "SELECT id, trial_count, winners, created_at FROM game_result";
        return this.jdbcTemplate.query(sql,
                (resultSet, rowNumber) -> new GameResultEntity(
                        resultSet.getLong("id"),
                        resultSet.getInt("trial_count"),
                        resultSet.getString("winners"),
                        resultSet.getDate("created_at"))
        );
    }

    public List<PlayerResultEntity> getPlayerResultsBy(long gameResultId) {
        String sql = "SELECT id, name, position, game_result_id FROM player_result WHERE game_result_id = ?";
        return this.jdbcTemplate.query(sql,
                (resultSet, rowNumber) -> new PlayerResultEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("position"),
                        resultSet.getLong("game_result_id")),
                gameResultId);
    }
}

