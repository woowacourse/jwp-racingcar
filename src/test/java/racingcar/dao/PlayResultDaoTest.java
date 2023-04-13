package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlayResultDaoTest {

    @Autowired
    private PlayResultDao playResultDao;

    @Test
    void insert() {
        int count = 5;
        String winners = "브리, 토미, 브라운";

        long id = playResultDao.insert(count, winners);

        assertThat(playResultDao.findWinners(id))
                .isEqualTo(winners);
    }
}
