package racingcar.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
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
    public void saveGame(MoveCount moveCount, GameResultDto gameResultDto) {
        long gameId = gameDao.insertGame(moveCount);
        carDao.insert(gameResultDto, gameId);
        winnerDao.insertWinner(gameResultDto, gameId);
    }

    public List<GameResultDto> selectAllGames(){
        List<GameResultDto> games = new ArrayList<>();
        List<Long> gameIds = gameDao.selectAllGameIds();
        for(Long gameId : gameIds){
            List<CarDto> carDto = carDao.selectByGameId(gameId);
            List<String> winners = winnerDao.selectByGameId(gameId);
            games.add(new GameResultDto(winners, carDto));
        }
        return games;
    }
}
