package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingCarRecordDao;
import racingcar.dao.RacingGameHistoryDao;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.RacingGame;
import racingcar.repository.WebRacingGameRepository;

@SpringBootTest
@Transactional
class RacingGameRepositoryTest {

    @Autowired
    private RacingGameHistoryDao racingGameHistoryDao;

    @Autowired
    private RacingCarRecordDao racingCarRecordDao;

    @Autowired
    private WebRacingGameRepository racingGameRepository;

    @DisplayName("모든 게임 객체를 데이터베이스에서 읽어올 수 있다.")
    @Test
    void testFindAll() {
        LocalDateTime now = LocalDateTime.now();
        Long generatedKey = racingGameHistoryDao.insert(10, now);
        racingCarRecordDao.insert(generatedKey, new RacingCar("name", 10), true);
        racingCarRecordDao.insert(generatedKey, new RacingCar("이름", 3), false);

        List<RacingGame> racingGames = racingGameRepository.findAll();

        RacingGame racingGame = racingGames.get(0);
        assertThat(racingGame.getId()).isEqualTo(generatedKey);
        assertThat(racingGame).extracting("playTime", InstanceOfAssertFactories.LOCAL_DATE_TIME).isEqualTo(now);
        List<RacingCar> racingCars = racingGame.getRacingCars();
        assertThat(racingCars.stream().map(RacingCar::getName)).contains("name", "이름");
    }
}