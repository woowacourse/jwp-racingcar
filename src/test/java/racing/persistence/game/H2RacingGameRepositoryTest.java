package racing.persistence.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;
import racing.domain.RacingCarGame;
import racing.domain.repository.car.CarRepository;
import racing.domain.repository.game.RacingGameRepository;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class H2RacingGameRepositoryTest {

    @Autowired
    private RacingGameRepository racingGameRepository;

    @Autowired
    private CarRepository h2CarRepository;

    @DisplayName("참여한 자동차를 포함한 게임을 조회 할 수 있다.")
    @Test
    void findAllGamesByIdTest() {
        Long savedGameId = racingGameRepository.saveGameByCount(5);
        Cars cars = new Cars(List.of(
                new Car(new CarName("CarA"), 5),
                new Car(new CarName("CarB"), 1),
                new Car(new CarName("CarC"), 2)
        ));
        h2CarRepository.saveCarsInGame(cars, savedGameId);

        RacingCarGame racingGame = racingGameRepository.findRacingGameById(savedGameId);

        Cars gameCars = racingGame.getCars();
        assertThat(gameCars.getCars()).hasSize(3);
        assertThat(gameCars.getCars()).extracting("step")
                .contains(5, 1, 2);
    }

}
