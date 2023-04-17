package racingcar.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameResultDto;
import racingcar.model.MoveCount;

@Repository
public class RacingGameRepository {
    private final GameDao gameDao;
    private final WinnerDao winnerDao;
    private final CarDao carDao;

    public RacingGameRepository(GameDao gameDao, WinnerDao winnerDao, CarDao carDao) {
        this.gameDao = gameDao;
        this.winnerDao = winnerDao;
        this.carDao = carDao;
    }

    @Transactional
    public void saveGameResult(MoveCount moveCount, GameResultDto gameResultDto) {
        long gameId = gameDao.insertGame(moveCount);
        carDao.insertCar(gameResultDto, gameId);
        winnerDao.insertWinner(gameResultDto, gameId);
    }
}
