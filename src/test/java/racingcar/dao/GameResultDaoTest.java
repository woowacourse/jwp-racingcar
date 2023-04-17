package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.web.dao.GameResultDao;
import racingcar.web.entity.GameResultEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameResultDaoTest {


    @Autowired
    private GameResultDao gameResultDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void save() {
        Long id = gameResultDao.insert(new GameResultEntity(3));
        Assertions.assertThat(1L).isEqualTo(id);
    }

}
