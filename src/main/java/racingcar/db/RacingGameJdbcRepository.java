package racingcar.db;

import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.TryCount;
import racingcar.dto.GameWinnerDto;
import racingcar.dto.GameResultDto;

import java.util.ArrayList;
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
    public void saveGame(TryCount tryCount, String winners, List<Car> cars) {
        int id = gameResultDao.save(tryCount, winners, cars);
        resultCarDao.save(id, cars);
    }

    @Override
    public List<GameResultDto> findAllGameResult() {
        List<GameResultDto> gameResults = new ArrayList<>();

        List<GameWinnerDto> gameWinners = gameResultDao.selectAllGameResult();
        for (GameWinnerDto gameWinner : gameWinners) {
            List<Car> cars = resultCarDao.findByGameId(gameWinner.getGameId());
            gameResults.add(new GameResultDto(gameWinner.getWinners(), cars));
        }

        return gameResults;
    }

    @Override
    public void deleteAll() {
        gameResultDao.deleteAll();
    }
}
