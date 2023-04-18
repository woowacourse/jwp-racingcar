package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;

@Repository
public class CarDao {

    private final InsertCarDao insertCarDao;
    private final SelectCarDao selectCarDao;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        insertCarDao = new InsertCarDao(jdbcTemplate);
        selectCarDao = new SelectCarDao(jdbcTemplate);
    }

    public List<CarEntity> insertAll(final List<CarEntity> carEntities) {
        //batch insert 시에 id 를 조회할 방법이 없어서, carEntities 는 모두 같은 gameId 를 가질 것이라고 가정했습니다
        final Integer gameId = carEntities.get(0).getGameId();
        insertCarDao.insertAll(carEntities);
        return selectCarDao.findAllByGameId(gameId);
    }
}
