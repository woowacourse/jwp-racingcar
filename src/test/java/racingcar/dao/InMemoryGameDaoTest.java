package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class InMemoryGameDaoTest {

    @Autowired
    private GameDao gameDao;

    @Test
    @DisplayName("시도 횟수, 승자를 DB에 저장하며 id를 반환하는 성공 테스트")
    void save() {
        int trialCount = 5;

        int gameId = gameDao.save(trialCount);

        assertThat(gameId).isEqualTo(1);
    }

    @Test
    @DisplayName("시도 횟수, 승자를 DB에 저장하며 id를 반환하는 성공 테스트")
    void saveAll() {
        gameDao.save(5);
        gameDao.save(10);

        assertThat(gameDao.findAll().size()).isEqualTo(2);
    }
}
