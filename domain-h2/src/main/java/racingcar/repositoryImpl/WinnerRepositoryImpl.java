package racingcar.repositoryImpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.dao.WinnerDao;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.Winners;
import racingcar.repository.WinnerRepository;

@Repository
public class WinnerRepositoryImpl implements WinnerRepository {

    private final WinnerDao winnerDao;

    public WinnerRepositoryImpl(final WinnerDao winnerDao) {
        this.winnerDao = winnerDao;
    }

    @Override
    public void save(final Winners winners) {
        final List<WinnerEntity> winnerEntities = RacingGameMapper.toWinnerEntity(winners);
        winnerDao.insertAll(winnerEntities);
    }
}
