package racingcar.dao.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.CarRepository;
import racingcar.dao.GameRepository;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;

@JdbcTest
@DisplayName("CarJdbcRepository 테스트")
@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarJdbcRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarRepository carRepository;
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarJdbcRepository(jdbcTemplate);
        gameRepository = new GameJdbcRepository(jdbcTemplate);
    }

    @Test
    void saveAll은_입력받은_자동차를_전부_저장한다() {
        final int gameId = gameRepository.save(new GameEntity(null, 3, LocalTime.now()));
        final List<CarEntity> cars = List.of(new CarEntity(null, "가가", 0, gameId),
                new CarEntity(null, "나나", 0, gameId));

        carRepository.saveAll(cars);

        List<CarEntity> carsByGameID = carRepository.findCarsByGameID(gameId);
        assertThat(carsByGameID).hasSize(2); // 개수
    }

    @Test
    void findCarsByGameID는_게임_아이디가_동일한_모든_자동차_정보를_불러온다() {
        //given
        final int gameId = gameRepository.save(new GameEntity(null, 3, LocalTime.now()));
        final List<CarEntity> cars = List.of(new CarEntity(null, "가가", 0, gameId),
                new CarEntity(null, "나나", 10, gameId));
        carRepository.saveAll(cars);

        List<CarEntity> carsWithSameGameId = carRepository.findCarsByGameID(gameId);

        CarEntity carEntity = carsWithSameGameId.get(1);
        assertThat(carEntity.getCarId()).isEqualTo(2);
        assertThat(carEntity.getName()).isEqualTo("나나");
        assertThat(carEntity.getPosition()).isEqualTo(10);
        assertThat(carEntity.getGameId()).isEqualTo(gameId);

    }
}
