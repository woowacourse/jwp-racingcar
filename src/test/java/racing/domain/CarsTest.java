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
                new Car(new CarName("bebe0"), 1),
                new Car(new CarName("bebe1"), 2),
                new Car(new CarName("bebe2"), 2)
        );
        cars = new Cars(carList);
    }

    @DisplayName("경기 승자를 반환해야 한다.")
    @Test
    void getWinners() {
        Cars cars = new Cars(List.of(
                new Car(new CarName("bebe0"), 1),
                new Car(new CarName("bebe1"), 2),
                new Car(new CarName("bebe2"), 2)
        ));

        List<String> winnerCars = cars.getWinners();

        assertEquals(winnerCars, List.of("bebe1", "bebe2"));
    }
}
