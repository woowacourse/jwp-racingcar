package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.FixNumberGenerator;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingGameResponseDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class RacingCarServiceTest {

    @Autowired
    private RacingCarService racingCarService;
    private RacingGame racingGame;


    @BeforeEach
    void setup() {
        final NumberGenerator numberGenerator = new FixNumberGenerator(
                new ArrayList<>(Arrays.asList(6, 3, 6, 5, 6, 8))
        );
        final Cars cars = new Cars(List.of("다즐", "루쿠"));
        final Count count = new Count(3);
        racingGame = new RacingGame(numberGenerator, cars, count);
    }


    @DisplayName("게임결과를 반환한다.")
    @Test
    void play() {
        final RacingGameResponseDto racingGameResponseDto = racingCarService.play(racingGame);

        assertAll(
                () -> assertThat(racingGameResponseDto.getWinners()).isEqualTo("다즐"),
                () -> assertThat(racingGameResponseDto.getRacingCars().size()).isEqualTo(2),
                () -> assertThat(racingGameResponseDto.getRacingCars().get(0).getName()).isEqualTo("다즐"),
                () -> assertThat(racingGameResponseDto.getRacingCars().get(0).getPosition()).isEqualTo(3),
                () -> assertThat(racingGameResponseDto.getRacingCars().get(1).getName()).isEqualTo("루쿠"),
                () -> assertThat(racingGameResponseDto.getRacingCars().get(1).getPosition()).isEqualTo(2)
        );
    }

    @DisplayName("저장된 게임이력을 반환한다.")
    @Test
    void findAllHistories() {
        racingCarService.play(racingGame);
        List<RacingGameResponseDto> result = racingCarService.findAllHistories();

        assertAll(
                () -> assertThat(result.get(0).getWinners()).isEqualTo("다즐"),
                () -> assertThat(result.get(0).getRacingCars().size()).isEqualTo(2),
                () -> assertThat(result.get(0).getRacingCars().get(0).getName()).isEqualTo("다즐"),
                () -> assertThat(result.get(0).getRacingCars().get(0).getPosition()).isEqualTo(3),
                () -> assertThat(result.get(0).getRacingCars().get(1).getName()).isEqualTo("루쿠"),
                () -> assertThat(result.get(0).getRacingCars().get(1).getPosition()).isEqualTo(2));
    }


}
