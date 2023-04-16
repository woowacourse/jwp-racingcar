package racingcar.dao;

import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultDto;
import racingcar.dto.response.GameResponseDto;

import java.util.List;

@Repository
public class RacingGameJdbcRepository implements RacingGameRepository {
    private final GameResultDao gameResultDao;
    private final ResultCarDao resultCarDao;

    public RacingGameJdbcRepository(GameResultDao gameResultDao, ResultCarDao resultCarDao) {
        this.gameResultDao = gameResultDao;
        this.resultCarDao = resultCarDao;
    }

    @Override
    public void saveGame(GameResultDto resultDto) {
        int id = gameResultDao.save(resultDto);
        resultCarDao.save(id, resultDto.getRacingCars());
    }

    @Override
    public List<GameResponseDto> findAllGame() {
        return null;
    }
}
