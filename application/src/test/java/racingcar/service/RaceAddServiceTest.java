package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class RaceAddServiceTest {

    private final List<String> carsName = List.of("브리", "토미", "브라운");
    private final int count = 10;
    private RaceAddService raceAddService;

    @BeforeEach
    void setUp() {
        raceAddService = new RaceAddService(new StubNumberPicker(10),
                new StubRacingGameRepository());
    }

    @Test
    void 레이스_진행() {
        final RacingGame racingGame = raceAddService.addRace(carsName, count);
        final List<Car> winners = racingGame.findWinner();
        assertAll(
                () -> assertThat(winners).hasSize(3),
                () -> assertThat(winners.get(0).getCarName()).isEqualTo("브리"),
                () -> assertThat(winners.get(0).getPosition()).isEqualTo(10),
                () -> assertThat(winners.get(1).getCarName()).isEqualTo("토미"),
                () -> assertThat(winners.get(1).getPosition()).isEqualTo(10),
                () -> assertThat(winners.get(2).getCarName()).isEqualTo("브라운"),
                () -> assertThat(winners.get(2).getPosition()).isEqualTo(10)
        );
    }
}
