package racingcar.dao;

import racingcar.entity.GameEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryGameDao implements GameDao {

    private int moveCount;

    @Override
    public long insert(int moveCount) {
        this.moveCount = moveCount;
        return 0;
    }

    @Override
    public List<GameEntity> selectAll() {
        GameEntity gameEntity = new GameEntity(0, moveCount, Date.valueOf(LocalDate.now()));
        List<GameEntity> gameEntities = new ArrayList<>();
        gameEntities.add(gameEntity);
        return gameEntities;
    }
}
