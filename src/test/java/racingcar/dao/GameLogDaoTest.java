package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class GameLogDaoTest {

    @Autowired
    private GameLogDao gameLogDao;

    @Test
    void insert() {
        assertThatNoException().isThrownBy(() -> gameLogDao.insert(5, "달리", 10));
    }

    @Test
    void find() {
        gameLogDao.insert(3, "달리", 4);
        gameLogDao.insert(3, "디노", 5);
        gameLogDao.insert(3, "저문", 6);

        assertThat(gameLogDao.find(3)).hasSize(3);
    }
}
