package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

@JdbcTest
class MySqlRacingCarRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    RacingCarRepository racingCarRepository;

    @BeforeEach
    void setUp() {
        racingCarRepository = new MySqlRacingCarRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("승자가 정상적으로 저장되어야 한다.")
    void saveWinners_success() {
        // given
        int gameId = racingCarRepository.saveGame(1);

        // expect
        racingCarRepository.saveWinner(gameId, List.of("glen", "raon"));
        List<String> winners = racingCarRepository.findWinners(gameId);

        assertThat(winners)
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

    @Test
    @DisplayName("자동차의 정보가 정상적으로 조회되어야 한다.")
    void findAllRacingCars_success() {
        // given
        int gameId1 = racingCarRepository.saveGame(1);
        int gameId2 = racingCarRepository.saveGame(2);
        Car car1 = new Car("glen");
        Car car2 = new Car("raon");

        racingCarRepository.saveCars(gameId1, List.of(car1));
        racingCarRepository.saveCars(gameId2, List.of(car2));

        // expect
        Map<Integer, List<RacingCarDto>> racingCars = racingCarRepository.findAllRacingCars();
        assertThat(racingCars.keySet())
                .hasSize(2);
        assertThat(racingCars.get(gameId1).get(0).getName())
                .isEqualTo("glen");
        assertThat(racingCars.get(gameId2).get(0).getName())
                .isEqualTo("raon");
    }

    @Test
    @DisplayName("승자의 이름이 정상적으로 조회되어야 한다.")
    void findAllWinners_success() {
        // given
        int gameId1 = racingCarRepository.saveGame(1);
        int gameId2 = racingCarRepository.saveGame(2);

        racingCarRepository.saveWinner(gameId1, List.of("glen"));
        racingCarRepository.saveWinner(gameId2, List.of("raon"));

        // expect
        Map<Integer, List<String>> winners = racingCarRepository.findAllWinners();
        assertThat(winners.keySet())
                .hasSize(2);
        assertThat(winners.get(gameId1).get(0))
                .isEqualTo("glen");
        assertThat(winners.get(gameId2).get(0))
                .isEqualTo("raon");
    }
}
