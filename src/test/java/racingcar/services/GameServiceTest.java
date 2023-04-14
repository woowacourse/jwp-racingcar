package racingcar.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import racingcar.dto.CarDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.car.Name;

class GameServiceTest {

    GameService gameService;
    Cars cars;

    @BeforeEach
    void setUp() {
        gameService = new GameService(
                (trialCount) -> 1,
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

        assertThat(cars.getCurrentResult())
                .containsExactlyInAnyOrder(
                        new Car(new Name("폴로")),
                        new Car(new Name("이리내"))
                );
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

    @ParameterizedTest(name = "moveInput 으로 숫자가 아닌 값이 들어오거나 0이하의 수가 들어오면 예외가 발생한다.")
    @ValueSource(strings = {"a", "0", "-1"})
    void moveCarsFail(String countInput) {
        String nameInputs = "폴로,이리내,허브";
        gameService.createCars(cars, nameInputs);
        assertThatThrownBy(() -> gameService.moveCars(cars, countInput))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .containsAnyOf(
                        "1회 이상 이동해야 합니다.", "숫자만 입력가능 합니다."
                );
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
