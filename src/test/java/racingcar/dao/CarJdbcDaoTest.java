package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CarJdbcDaoTest {

    @Autowired
    private GameJdbcDao gameJdbcDao;

    @Autowired
    private CarJdbcDao carJdbcDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final CarDto carDto = CarDto.of("밀리", 1);
    private int gameId;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("ALTER TABLE car ALTER COLUMN id RESTART ");
        jdbcTemplate.update("ALTER TABLE game ALTER COLUMN id RESTART ");
        gameId = gameJdbcDao.insertGame(5);
        carJdbcDao.insertCar(carDto, gameId);
    }

    @Test
    @DisplayName("car를 저장한다.")
    void insertCar() {
        int carId = carJdbcDao.insertCar(CarDto.of("조이", 1), gameId);
        assertThat(carId).isEqualTo(2);
    }

    @Test
    @DisplayName("position을 업데이트한다.")
    void updatePosition() {
        assertDoesNotThrow(() -> carJdbcDao.updatePosition(CarDto.of("밀리", 5), gameId));
        assertThat(carJdbcDao.findCar("밀리", gameId).getPosition()).isEqualTo(5);
    }

    @Test
    @DisplayName("전체 자동차를 조회한다.")
    void findCars() {
        carJdbcDao.insertCar(CarDto.of("조이", 1), gameId);
        CarDto car1 = carJdbcDao.findCars(gameId).get(0);
        CarDto car2 = carJdbcDao.findCars(gameId).get(1);
        assertAll(
                () -> assertThat(car1.getName()).isEqualTo("밀리"),
                () -> assertThat(car1.getPosition()).isEqualTo(1),
                () -> assertThat(car2.getName()).isEqualTo("조이"),
                () -> assertThat(car2.getPosition()).isEqualTo(1)
        );
    }
}
