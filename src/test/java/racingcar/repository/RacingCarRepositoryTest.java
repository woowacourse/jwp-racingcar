package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

@JdbcTest
class RacingCarRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    RacingCarRepository racingCarRepository;

    @BeforeEach
    void setUp() {
        racingCarRepository = new RacingCarRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("승자가 정상적으로 저장되어야 한다.")
    void saveWinners_success() {
        // given
        int gameId = racingCarRepository.saveGame(1);

        // expect
        racingCarRepository.saveWinners(gameId, List.of("glen", "raon"));
        List<String> winners = racingCarRepository.findWinners(gameId);

        assertThat(winners)
                .hasSize(2)
                .contains("glen", "raon");
    }

    @Test
    @DisplayName("자동차가 정상적으로 저장되어야 한다.")
    void saveCars_success() {
        // given
        int gameId = racingCarRepository.saveGame(1);
        Car car1 = new Car("glen");
        Car car2 = new Car("raon");

        // expect
        racingCarRepository.saveCars(gameId, List.of(car1, car2));
        List<RacingCarDto> findCars = racingCarRepository.findRacingCars(gameId);

        assertThat(findCars)
                .hasSize(2);
        assertThat(findCars.get(0).getName())
                .containsAnyOf("glen", "raon");
    }
}
