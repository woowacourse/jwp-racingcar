package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarPositionDto;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class RacingCarsServiceTest {

    private final List<String> carsName = List.of("브리", "토미", "브라운");
    private final int count = 10;
    private RacingCarsService racingCarsService;

    @BeforeEach
    void setUp() {
        racingCarsService = new RacingCarsService(new StubNumberPicker(10), new FakeCarsRepository(),
                new StubGamesRepository());
    }

    @Test
    void 레이스_진행() {
        final int result = racingCarsService.race(carsName, count);

        assertThat(result).isOne();
    }

    @Nested
    class SelectTest {

        private final int gameId = 1;

        @BeforeEach
        void setUp() {
            racingCarsService.race(carsName, count);
        }

        @Test
        void 레이스_이후_자동차_조회() {
            final List<CarPositionDto> result = racingCarsService.findCarsWithPosition(gameId);

            assertThat(result).hasSize(3);
        }

        @Test
        void 레이스_이후_우승자_조회() {
            final List<CarPositionDto> result = racingCarsService.findWinners(gameId);

            assertAll(
                    () -> assertThat(result).hasSize(3),
                    () -> assertThat(result.get(0).getCarName()).isEqualTo("브리"),
                    () -> assertThat(result.get(0).getStatus()).isEqualTo(10),
                    () -> assertThat(result.get(1).getCarName()).isEqualTo("토미"),
                    () -> assertThat(result.get(1).getStatus()).isEqualTo(10),
                    () -> assertThat(result.get(2).getCarName()).isEqualTo("브라운"),
                    () -> assertThat(result.get(2).getStatus()).isEqualTo(10)
            );
        }

    }
}
