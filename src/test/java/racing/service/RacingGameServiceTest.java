package racing.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racing.domain.Car;
import racing.domain.Cars;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RacingGameServiceTest {

    @Autowired
    private RacingGameService racingGameService;

    private final Cars cars = new Cars(List.of(new Car("bebe"), new Car("royce"), new Car("dd")));

    @DisplayName("자동차가 생성되어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"bebe,royce,dd"})
    void createCars(String names) {
        // when, then
        Assertions.assertThat(racingGameService.createCars(names).getCars().size())
                .isEqualTo(3);
    }

    @DisplayName("게임을 생성해야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {7})
    void createRacingGame(int count) {
        // when, then
        Assertions.assertThat(racingGameService.createRacingGame(count))
                .isEqualTo(1);
    }

    @DisplayName("게임의 승자를 반환해야 한다.")
    @Test
    void getWinners() {
        // when
        Assertions.assertThat(racingGameService.getWinners(cars))
                .isEqualTo("beberoycedd");
    }

    @DisplayName("자동차를 움직일 수 있어야 한다.")
    @Test
    void move() {
        // when
        racingGameService.move(6, cars, 7);

        // then
        assertAll(
                () -> Assertions.assertThat(cars.getCars().get(0).getStep()).isEqualTo(7),
                () -> Assertions.assertThat(cars.getCars().get(1).getStep()).isEqualTo(7),
                () -> Assertions.assertThat(cars.getCars().get(2).getStep()).isEqualTo(7)
        );
    }

}
