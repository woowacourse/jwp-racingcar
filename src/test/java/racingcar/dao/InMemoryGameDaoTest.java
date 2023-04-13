package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class InMemoryGameDaoTest {

    @Autowired
    private GameDao gameDao;

    @Test
    void save() {
        String winner = "ocean";
        int trialCount = 5;
        int gameId = gameDao.save(trialCount, winner);

        assertThat(gameId).isEqualTo(1);
    }
}
