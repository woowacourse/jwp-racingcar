package racingcar.repository;

import org.springframework.stereotype.Repository;

@Repository
public class GamesDao {

    private final InsertGameDao insertGameDao;

    public GamesDao(final InsertGameDao insertGameDao) {
        this.insertGameDao = insertGameDao;
    }

    public int save(final int count) {
        return insertGameDao.save(count);
    }
}
