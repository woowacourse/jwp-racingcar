package racingcar.domain.racinggame;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingGameTest {
    @Test
    void 시도_횟수만큼_자동차가_이동한다() {
        // given
        RacingGame racingGame = new RacingGame("abel,split,pobi", 3);
        
        // when
        racingGame.race(() -> true);
        Cars cars = racingGame.getCars();
        List<Car> movedCars = cars.getCars();
        
        List<Car> expectedCars = new ArrayList<>(new Cars("abel,split,pobi").getCars());
        moveCars(expectedCars, 3);
        
        // then
        assertThat(movedCars).isEqualTo(expectedCars);
    }
    
    private void moveCars(List<Car> cars, int movingNumber) {
        for (int i = 0; i < movingNumber; i++) {
            for (Car car : cars) {
                int index = cars.indexOf(car);
                cars.set(index, car.move(() -> true));
            }
        }
    }
}
