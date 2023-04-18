package racingcar.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("게임이 정상적으로 저장되어야 한다.")
    void saveGame_success() {
        Assertions.assertDoesNotThrow(() -> racingCarRepository.saveGame(10));
    }

    @Test
    @DisplayName("승자가 정상적으로 저장되어야 한다.")
    void saveWinners_success() {
        // given
        int gameId = racingCarRepository.saveGame(1);

        // expect
        racingCarRepository.saveWinners(gameId, List.of("glen", "raon"));
        List<String> winners = racingCarRepository.findWinnersByGameId(gameId);

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
        List<RacingCarDto> findCars = racingCarRepository.findRacingCarsByGameId(gameId);

        assertThat(findCars)
                .hasSize(2);
        assertThat(findCars.get(0).getName())
                .containsAnyOf("glen", "raon");
    }

    @Test
    @DisplayName("게임에 저장된 우승자들의 모든 결과를 출력해야 한다.")
    void findWinners_success() {
        // given
        List<String> winnersTest1 = List.of("glen");
        List<String> winnersTest2 = List.of("raon");

        int gameId1 = racingCarRepository.saveGame(1);
        int gameId2 = racingCarRepository.saveGame(1);

        racingCarRepository.saveWinners(gameId1, winnersTest1);
        racingCarRepository.saveWinners(gameId2, winnersTest2);

        // expect
        Map<Integer, List<String>> playHistory = racingCarRepository.findWinners();

        assertThat(playHistory).hasSize(2);
        assertThat(playHistory.get(gameId1)).hasSize(winnersTest1.size());
        assertThat(playHistory.get(gameId2)).hasSize(winnersTest2.size());
        assertThat(playHistory.get(gameId1).get(0)).contains("glen");
        assertThat(playHistory.get(gameId2).get(0)).contains("raon");
    }

    @Test
    @DisplayName("게임에 저장된 자동차들의 모든 결과를 출력해야 한다.")
    void findRacingCars_success() {
        // given
        Car car1 = new Car("glen");
        Car car2 = new Car("raon");
        Car car3 = new Car("test");

        List<Car> carsTest1 = List.of(car1, car2);
        List<Car> carsTest2 = List.of(car1, car2, car3);

        int gameId1 = racingCarRepository.saveGame(1);
        int gameId2 = racingCarRepository.saveGame(1);

        racingCarRepository.saveCars(gameId1, carsTest1);
        racingCarRepository.saveCars(gameId2, carsTest2);

        // expect
        Map<Integer, List<RacingCarDto>> playHistory = racingCarRepository.findRacingCars();

        assertThat(playHistory).hasSize(2);
        assertThat(playHistory.get(gameId1)).hasSize(carsTest1.size());
        assertThat(playHistory.get(gameId2)).hasSize(carsTest2.size());
        assertThat(playHistory.get(gameId1).get(0).getName()).containsAnyOf("glen", "raon");
        assertThat(playHistory.get(gameId2).get(0).getName()).containsAnyOf("glen", "raon", "test");
    }
}
