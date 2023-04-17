package racing.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    private Car car;

    @BeforeEach
    void init() {
        car = new Car(new CarName("bebe"));
    }

    @DisplayName("우승자 step이랑 step이 같다면 우승자 리스트에 추가한다.")
    @Test
    void 만약_조건을_만족하면_리스트추가() {
        List<String> winners = new ArrayList<>();
        winners = car.ifMeetAddWinners(winners, 0);
        Assertions.assertEquals(winners, List.of("bebe"));
    }

}
