package racingcar.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlayRecordsDaoTest {

    @Autowired
    private PlayRecordsDao gameDao;

    @DisplayName("DB: 게임 이력 저장 테스트")
    @Test
    void insert() {
        Assertions.assertDoesNotThrow(() -> gameDao.insertAndReturnId(5));
    }
}
