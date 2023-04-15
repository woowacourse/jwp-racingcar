package racingcar.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlayResultDaoTest {

    @Autowired
    private PlayResultDao playResultDao;

    @Test
    void insert() {
        Assertions.assertDoesNotThrow(() -> playResultDao.insertAndReturnId(5));
    }
}
