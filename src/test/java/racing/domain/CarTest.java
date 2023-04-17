package racing.domain;

import org.junit.jupiter.api.BeforeEach;

class CarTest {

    private Car car;

    @BeforeEach
    void init() {
        car = new Car(new CarName("bebe"));
    }

}
