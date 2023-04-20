package racingcar.dao;

import racingcar.dao.entity.GameResultEntity;
import racingcar.dto.GameResultDto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameResultDAOInMemory implements GameResultDAO {
    private final List<GameResultEntity> gameResults = new ArrayList<>();

    @Override
    public int save(GameResultDto resultDto) {
        int id = gameResults.size();
        GameResultEntity result = GameResultEntity.of(id, resultDto.getTrialCount(), LocalTime.now());

        gameResults.add(result);

        return id;
    }

    @Override
    public List<GameResultEntity> findAll() {
        return Collections.unmodifiableList(gameResults);
    }
}
