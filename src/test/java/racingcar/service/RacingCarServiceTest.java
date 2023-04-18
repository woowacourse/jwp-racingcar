package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.NumberGenerator;
import racingcar.dto.response.RacingGameResponse;
import racingcar.repository.RacingCarRepository;
import racingcar.utils.TestNumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class RacingCarServiceTest {

    @Autowired
    private RacingCarRepository racingCarRepository;

    private RacingCarService racingCarService;

    @BeforeEach
    void setUp() {
        final NumberGenerator numberGenerator = new TestNumberGenerator(
                new ArrayList<>(Arrays.asList(8, 6, 5, 6, 3, 6, 8, 6, 5, 6, 3, 6))
        );
        racingCarService = new RacingCarService(racingCarRepository, numberGenerator);
    }

    @DisplayName("게임결과를 반환한다.")
    @Test
    void play() {
        final String rawNames = "다즐,루쿠";
        final int count = 3;

        final RacingGameResponse racingGameResponse = racingCarService.play(rawNames, count);

        assertAll(
                () -> assertThat(racingGameResponse.getWinners()).isEqualTo("다즐"),
                () -> assertThat(racingGameResponse.getRacingCars().size()).isEqualTo(2),
                () -> assertThat(racingGameResponse.getRacingCars().get(0).getName()).isEqualTo("다즐"),
                () -> assertThat(racingGameResponse.getRacingCars().get(0).getPosition()).isEqualTo(3),
                () -> assertThat(racingGameResponse.getRacingCars().get(1).getName()).isEqualTo("루쿠"),
                () -> assertThat(racingGameResponse.getRacingCars().get(1).getPosition()).isEqualTo(2)
        );
    }

    @DisplayName("전체 게임결과를 반환한다.")
    @Test
    void findPlays() {
        racingCarService.play("다즐,루쿠", 3);
        racingCarService.play("허브,오즈", 3);

        final List<RacingGameResponse> racingGameResponses = racingCarService.findPlays();

        assertAll(
                () -> assertThat(racingGameResponses).hasSize(2),
                () -> assertThat(racingGameResponses.get(0).getRacingCars()).hasSize(2),
                () -> assertThat(racingGameResponses.get(1).getRacingCars()).hasSize(2)
        );
    }
}
