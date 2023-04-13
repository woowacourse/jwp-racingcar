package racingcar.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.dao.GameDao;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.manager.ThresholdCarMoveManager;

class GameServiceTest {

    GameService gameService;
    Cars cars;

//    @BeforeEach
//    public void setUp() {
//        cars = new Cars(new ArrayList<>());
////        gameService = new GameService(cars, new ThresholdCarMoveManager(), new GameDao(new JdbcTemplate(), DataSource new ));
//    }
//
//    @Test
//    void createCars() {
//        String namesInput = "폴로,이리내";
//        gameService.createCars(namesInput);
//
//        assertThat(cars.getCurrentResult()).containsExactlyInAnyOrder(new Car("폴로"), new Car("이리내"));
//    }
}
