package racingcar.dao.console;

import racingcar.dao.RacingGameDao;
import racingcar.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RacingGameConsoleDao implements RacingGameDao {

    private static final int INITIAL_ID = 1;

    private List<GameEntity> gameEntities;
    private int id = INITIAL_ID;

    public static RacingGameConsoleDao racingGameConsoleDao;

    private RacingGameConsoleDao() {
    }

    public static RacingGameConsoleDao of() {
        if (!Objects.isNull(racingGameConsoleDao)) {
            return racingGameConsoleDao;
        }
        racingGameConsoleDao = new RacingGameConsoleDao();
        racingGameConsoleDao.id = racingGameConsoleDao.getId();
        racingGameConsoleDao.gameEntities = new ArrayList<>();
        return racingGameConsoleDao;
    }

    private int getId() {
        return id++;
    }

    @Override
    public List<GameEntity> findAll() {
        return gameEntities;
    }

    @Override
    public int saveGame(GameEntity gameEntity) {
        GameEntity createdGameEntity = new GameEntity.Builder()
                .id(getId())
                .count(gameEntity.getCount())
                .build();
        gameEntities.add(createdGameEntity);
        return createdGameEntity.getId();
    }

    @Override
    public GameEntity getRacingGameById(int gameId) {
        return gameEntities.stream()
                .filter(gameEntity -> gameEntity.getId() == gameId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾는 게임이 없습니다."));
    }

}
