package racing.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarsTest {

    private Cars cars;

    @BeforeEach
    void init() {
        List<Car> carList = List.of(
                new Car(new CarName("bebe0")),
                new Car(new CarName("bebe1")),
                new Car(new CarName("bebe2"))
        );
        cars = new Cars(carList);
    }

    @DisplayName("경기 승자를 반환해야 한다.")
    @Test
    void getWinners() {
        cars.getCar(0).moveForward();
        cars.getCar(1).moveForward();
        cars.getCar(1).moveForward();
        cars.getCar(2).moveForward();
        cars.getCar(2).moveForward();
        assertEquals(cars.getWinners(), List.of("bebe1", "bebe2"));
    }
}
