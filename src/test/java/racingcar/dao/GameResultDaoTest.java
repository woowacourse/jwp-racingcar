package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import racingcar.entity.GameResultEntity;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameResultDaoTest {

    @Autowired
    private GameResultDao gameResultDao;

    @Test
    @Rollback
    void save() {
        Long id = gameResultDao.save(new GameResultEntity(3));
        assertThat(1L).isEqualTo(id);
    }
}
