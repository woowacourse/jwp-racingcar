package racingcar.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;
import racingcar.dto.ResponseDto;
import racingcar.model.MoveCount;

import java.util.ArrayList;
import java.util.List;

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
    public void saveGame(MoveCount moveCount, List<String> winners, List<CarDto> cars) {
        long gameId = gameDao.insertGame(moveCount);
        carDao.insert(cars, gameId);
        winnerDao.insertWinner(winners, gameId);
    }

    public List<ResponseDto> selectAllGames() {
        List<ResponseDto> games = new ArrayList<>();
        List<Long> gameIds = gameDao.selectAllGameIds();
        for (Long gameId : gameIds) {
            List<CarDto> carDto = carDao.selectByGameId(gameId);
            List<String> winners = winnerDao.selectByGameId(gameId);
            games.add(new ResponseDto(winners, carDto));
        }
        return games;
    }
}
