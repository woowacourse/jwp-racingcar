package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.GameResultDao;
import racingcar.dto.GameResultResponseDto;
import racingcar.entity.GameResultEntity;

import java.util.List;

@Repository
public class GameResultRepository {

    private final GameResultDao gameResultDao;

    public GameResultRepository(final GameResultDao gameResultDao) {
        this.gameResultDao = gameResultDao;
    }

    public Long save(final GameResultEntity gameResultEntity) {
        return gameResultDao.save(gameResultEntity);
    }

    public List<GameResultResponseDto> findAll() {
//        dto -gameResultDao.findAll();
        return null;
    }
}
