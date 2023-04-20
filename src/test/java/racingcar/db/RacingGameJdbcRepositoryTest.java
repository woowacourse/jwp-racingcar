package racingcar.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RacingGameJdbcRepositoryTest {

    @Autowired
    RacingGameRepository racingGameRepository;

    @BeforeEach
    void setUp() {
        racingGameRepository.deleteAll();
    }

    @Test
    void findGame_success() {
        List<Car> cars = List.of(new Car(new Name("qwer"), 4));
        racingGameRepository.saveGame(new TryCount(5), "qwer", cars);
        List<GameResultDto> games = racingGameRepository.findAllGameResult();

        assertThat(games.get(0).getRacingCars()).usingRecursiveComparison().isEqualTo(cars);
        assertThat(games.get(0).getWinners()).isEqualTo("qwer");
    }
}
