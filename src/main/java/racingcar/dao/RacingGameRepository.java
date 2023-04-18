package racingcar.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RacingGameRepository implements GameRepository {
    private final GameDao gameDao;
    private final WinnerDao winnerDao;
    private final CarDao carDao;

    public RacingGameRepository(GameDao gameDao, WinnerDao winnerDao, CarDao carDao) {
        this.gameDao = gameDao;
        this.winnerDao = winnerDao;
        this.carDao = carDao;
    }

    @Override
    @Transactional
    public void saveGame(RacingGameDto racingGameDto) {
        long gameId = gameDao.insertGame(racingGameDto.getMoveCount());
        carDao.insert(racingGameDto.getCars(), gameId);
        winnerDao.insertWinner(racingGameDto.getWinnerNames(), gameId);
    }

    @Override
    public List<RacingGameDto> selectAllGames() {
        List<RacingGameDto> games = new ArrayList<>();
        List<Long> gameIds = gameDao.selectAllGameIds();
        for (Long gameId : gameIds) {
            List<CarDto> carDtos = carDao.selectByGameId(gameId);
            List<String> winners = winnerDao.selectByGameId(gameId);
            int moveCount = gameDao.selectMoveCountById(gameId);
            games.add(new RacingGameDto(winners, carDtos, moveCount));
        }
        return games;
    }
}
