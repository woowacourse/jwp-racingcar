package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import racingcar.DatabaseTest;
import racingcar.domain.Car;
import racingcar.domain.Game;

@DatabaseTest
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    private Game localGame;

    @BeforeEach
    void setUp() {
        List<Car> cars = List.of(
                new Car("땡칠", 0),
                new Car("필립", 0),
                new Car("다니", 3)
        );
        this.localGame = new Game(cars, 5);
        this.localGame.playOnceWith(() -> true);

        gameRepository.save(localGame);
    }

    @DisplayName("게임 저장 테스트")
    @Test
    void save() {
        List<Game> games = gameRepository.findAll();

        assertThat(games).hasSize(1);
    }

    @DisplayName("자동차 저장 테스트")
    @Test
    void save_saves_cars() {
        Game firstFromRepository = gameRepository.findAll().get(0);

        assertThat(firstFromRepository.getCars()).hasSameSizeAs(localGame.getCars());
    }

    @DisplayName("게임 조회 테스트")
    @Test
    void findAll() {
        Game firstFromRepository = gameRepository.findAll().get(0);

        assertSoftly(softly -> {
            softly.assertThat(firstFromRepository.getInitialTrialCount()).isEqualTo(localGame.getInitialTrialCount());
            softly.assertThat(firstFromRepository.getRemainingTrialCount()).isEqualTo(localGame.getRemainingTrialCount());
            softly.assertThat(firstFromRepository.getCars()).hasSameElementsAs(localGame.getCars());
        });
    }
}