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
import racingcar.dto.RacingResultResponse;

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
        String winners = racingCarRepository.findWinners(gameId);

        assertThat(winners)
                .isEqualTo("glen,raon");
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
    @DisplayName("모든 플레이 이력이 조회되어야 한다.")
    void findAllGameResults_success() {
        // given
        int gameId1 = racingCarRepository.saveGame(1);
        int gameId2 = racingCarRepository.saveGame(2);
        Car car1 = new Car("glen");
        Car car2 = new Car("raon");

        racingCarRepository.saveCars(gameId1, List.of(car1, car2));
        racingCarRepository.saveWinner(gameId1, List.of(car1.getName()));
        racingCarRepository.saveCars(gameId2, List.of(car1, car2));
        racingCarRepository.saveWinner(gameId2, List.of(car1.getName(), car2.getName()));

        // when
        List<RacingResultResponse> gameResults = racingCarRepository.findAllGameResults();

        // then
        assertThat(gameResults)
                .hasSize(2);
        assertThat(gameResults.get(0).getWinners())
                .isEqualTo("glen");
        assertThat(gameResults.get(1).getWinners())
                .isEqualTo("glen,raon");
    }
}
