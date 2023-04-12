package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.service.GamesRepository;

@Repository
public class GamesRepositoryImpl implements GamesRepository {

    private final GamesDao gamesDao;

    public GamesRepositoryImpl(final GamesDao gamesDao) {
        this.gamesDao = gamesDao;
    }

    @Override
    public int save(final int count) {
        return gamesDao.save(count);
    }
}
