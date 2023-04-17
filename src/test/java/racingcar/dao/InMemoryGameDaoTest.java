package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.entity.GameEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class InMemoryGameDaoTest {

    @Autowired
    private GameDao gameDao;

    @Test
    @DisplayName("시도 횟수, 승자를 DB에 저장하며 id를 반환하는 성공 테스트")
    void save() {
        String winner = "ocean";
        int trialCount = 5;
        int gameId = gameDao.save(trialCount, winner);

        assertThat(gameId).isEqualTo(1);
    }

    @Test
    @DisplayName("game 값을 모두 불러온다.")
    void findAll() {
        gameDao.save(5, "ocean");
        gameDao.save(5, "mint");

        List<GameEntity> games = gameDao.findAll();

        assertThat(games.size()).isEqualTo(2);
    }
}
