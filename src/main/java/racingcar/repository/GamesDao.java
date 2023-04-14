package racingcar.repository;

import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class GamesDao {

    private final InsertGameDao insertGameDao;

    public GamesDao(final DataSource dataSource) {
        insertGameDao = new InsertGameDao(dataSource);
    }

    public int save(final int count) {
        return insertGameDao.save(count);
    }
}
