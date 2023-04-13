package racingcar.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.dto.CarDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;

class GameServiceTest {

    GameService gameService;
    Cars cars;

    @BeforeEach
    void setUp() {
        gameService = new GameService(
                (trialCount, resultDto) -> 1,
                (resultDto, gameId) -> {},
                (resultDto, gameId) -> {},
                new TestMoveManager(List.of(true, false, false))
        );
        cars = gameService.initialize();
    }

    @Test
    @DisplayName("createCars를 통해 자동차를 생성한다.")
    void createCars() {
        String namesInput = "폴로,이리내";
        gameService.createCars(cars, namesInput);

        assertThat(cars.getCurrentResult()).containsExactlyInAnyOrder(new Car("폴로"), new Car("이리내"));
    }

    @Test
    @DisplayName("countInput 만큼 게임을 실행하고 자동차를 게임 결과에 따라 이동 시킨다.")
    void moveCars() {
        String nameInputs = "폴로,이리내,허브";
        String countInput = "1";
        gameService.createCars(cars, nameInputs);
        gameService.moveCars(cars, countInput);

        String winner = gameService.getWinner(cars);

        assertThat(winner).isEqualTo("폴로");
    }

    @Test
    @DisplayName("getResult를 통해 자동차의 이동 결과를 반환한다.")
    void getResult() {
        String nameInputs = "폴로,이리내,허브";
        String countInput = "1";
        gameService.createCars(cars, nameInputs);
        gameService.moveCars(cars, countInput);

        List<CarDto> result = gameService.getResult(cars);

        Assertions.assertAll(
                () -> assertThat(result).hasSize(3),
                () -> assertThat(result.get(0).getName()).isEqualTo("폴로"),
                () -> assertThat(result.get(0).getPosition()).isEqualTo(1),
                () -> assertThat(result.get(1).getName()).isEqualTo("이리내"),
                () -> assertThat(result.get(1).getPosition()).isEqualTo(0),
                () -> assertThat(result.get(2).getName()).isEqualTo("허브"),
                () -> assertThat(result.get(2).getPosition()).isEqualTo(0)
        );
    }
}
