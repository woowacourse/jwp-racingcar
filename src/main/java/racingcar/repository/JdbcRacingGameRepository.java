package racingcar.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.repository.dao.PlayResultDao;
import racingcar.repository.dao.RacingCarDao;
import racingcar.service.dto.GameHistoryDto;
import racingcar.service.dto.RacingCarDto;

@Repository
public class JdbcRacingGameRepository implements RacingGameRepository {

    private final PlayResultDao playResultDao;
    private final RacingCarDao racingCarDao;

    public JdbcRacingGameRepository(final PlayResultDao playResultDao, final RacingCarDao racingCarDao) {
        this.playResultDao = playResultDao;
        this.racingCarDao = racingCarDao;
    }

    @Override
    public List<GameHistoryDto> findGameHistories() {
        return playResultDao.findGameHistories();
    }

    @Override
    public void saveGameResult(final List<String> winners, final List<RacingCarDto> racingCars) {
        final Long gameId = playResultDao.insertWithKeyHolder(winners);
        racingCarDao.insert(gameId, racingCars);
    }
}
