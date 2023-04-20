package racingcar.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DBRacingRacingGameRepository implements RacingGameRepository {
    private final DBGameDao gameDao;
    private final DBWinnerDao DBWinnerDao;
    private final DBCarDao DBCarDao;

    public DBRacingRacingGameRepository(DBGameDao gameDao, DBWinnerDao DBWinnerDao, DBCarDao DBCarDao) {
        this.gameDao = gameDao;
        this.DBWinnerDao = DBWinnerDao;
        this.DBCarDao = DBCarDao;
    }

    @Override
    @Transactional
    public void save(RacingGameDto racingGameDto) {
        long gameId = gameDao.insertGame(racingGameDto.getMoveCount());
        DBCarDao.insert(racingGameDto.getCars(), gameId);
        DBWinnerDao.insertWinner(racingGameDto.getWinnerNames(), gameId);
    }

    @Override
    public List<RacingGameDto> selectAll() {
        List<RacingGameDto> games = new ArrayList<>();
        List<Long> gameIds = gameDao.selectAllGameIds();
        for (Long gameId : gameIds) {
            List<CarDto> carDtos = DBCarDao.selectByGameId(gameId);
            List<String> winners = DBWinnerDao.selectByGameId(gameId);
            int moveCount = gameDao.selectMoveCountById(gameId);
            games.add(new RacingGameDto(winners, carDtos, moveCount));
        }
        return games;
    }
}
