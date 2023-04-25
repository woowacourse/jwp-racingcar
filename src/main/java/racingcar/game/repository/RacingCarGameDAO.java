package racingcar.game.repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.car.repository.CarDAO;
import racingcar.game.model.GameEntity;
import racingcar.game.model.GameResult;

@Repository
public class RacingCarGameDAO implements GameDAO {
    
    public static final String GAME_DOES_NOT_EXIST = "게임 결과가 존재하지 않습니다.";
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final CarDAO racingCarDAO;
    
    private final RowMapper<GameEntity> gameRowMapper = (resultSet, rowNum) -> GameEntity.create(
            resultSet.getInt("id"),
            resultSet.getInt("trial_count"),
            resultSet.getString("winners"),
            resultSet.getTimestamp("created_at")
    );
    
    public RacingCarGameDAO(final JdbcTemplate jdbcTemplate, final CarDAO racingCarDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.racingCarDAO = racingCarDAO;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("racing_game")
                .usingGeneratedKeyColumns("id");
    }
    
    public int insert(final int count, final GameResult gameResult) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", count);
        parameters.put("winners", gameResult.getWinners());
        parameters.put("created_at", new Timestamp(gameResult.getCreatedAt()));
        return this.simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }
    
    @Override
    public GameResult find(final int gameId) {
        final String sql = "select id, trial_count, winners, created_at from racing_game where id = ?";
        final Optional<GameEntity> gameEntity = Optional.ofNullable(
                this.jdbcTemplate.queryForObject(sql, this.gameRowMapper, gameId));
        if (gameEntity.isPresent()) {
            return GameResult.create(gameEntity.get(), this.racingCarDAO.findAll(gameId));
        }
        throw new IllegalArgumentException(GAME_DOES_NOT_EXIST);
    }
    
    @Override
    public List<GameResult> findAll() {
        final String sql = "select id, trial_count, winners, created_at from racing_game";
        final List<GameEntity> gameEntities = this.jdbcTemplate.query(sql, this.gameRowMapper);
        return gameEntities.stream()
                .map(gameEntity -> GameResult.create(gameEntity, this.racingCarDAO.findAll(gameEntity.getGameId())))
                .collect(Collectors.toList());
    }
}