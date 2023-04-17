package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.MockNumberGenerator;
import racingcar.controller.GameResponseDto;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameServiceTest {

    @Autowired
    private RacingGameService racingGameService;

    @Test
    void play() {
        String names = "odo,jena";
        RacingGame racingGame = racingGameService.play(
                names,
                3,
                new MockNumberGenerator(Lists.newArrayList(3, 3, 3, 4, 4, 4))
        );
        assertThat(racingGame.getWinner().getWinnerNames()).containsExactly("jena");
        List<Car> racingCars = racingGame.getCars();
        Car odo = racingCars.get(0);
        Car jena = racingCars.get(1);
        assertThat(odo.getName()).isEqualTo("odo");
        assertThat(odo.getDistance()).isEqualTo(1);
        assertThat(jena.getName()).isEqualTo("jena");
        assertThat(jena.getDistance()).isEqualTo(2);
    }

    @Test
    void getGameResponseDtos() {
        String names = "odo,jena";
        RacingGame racingGame = racingGameService.play(
                names,
                3,
                new MockNumberGenerator(Lists.newArrayList(3, 3, 3, 4, 4, 4))
        );
        racingGameService.save(racingGame);

        List<GameResponseDto> gameResponseDtos = racingGameService.getGameResponseDtos();
        GameResponseDto gameResponseDto = gameResponseDtos.get(gameResponseDtos.size() - 1);
        List<Car> racingCars = gameResponseDto.getRacingCars();
        Car odo = racingCars.get(0);
        Car jena = racingCars.get(1);
        assertThat(odo.getName()).isEqualTo("odo");
        assertThat(jena.getName()).isEqualTo("jena");
        assertThat(odo.getDistance()).isEqualTo(1);
        assertThat(jena.getDistance()).isEqualTo(2);
        assertThat(gameResponseDto.getWinners()).isEqualTo("jena");
    }
}
