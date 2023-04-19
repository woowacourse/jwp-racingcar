package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.dto.GameResultDto;
import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryRacingGameRepositoryTest {

    {
        final var CarsList = List.of(new Car("a", 3), new Car("b", 4));
        cars = new Cars(CarsList);
    }

    private final Cars cars;
    private final GameTime gameTime = new GameTime(10);
    private final RacingGame racingGame = new RacingGame(cars, gameTime);
    private final MemoryRacingGameRepository memoryRacingGameRepository = new MemoryRacingGameRepository();

    @BeforeEach
    void setUp() {
        final var carsList = List.of(new Car("a", 3), new Car("b", 4));
        final var winners = new Winners(cars);
        memoryRacingGameRepository.getGameSaved().put(100L, new GameResultDto(carsList, winners));
        memoryRacingGameRepository.getGameTimeSaved().put(100L, 10);
    }

    @Test
    void save() {
        //given
        final var id = memoryRacingGameRepository.save(racingGame);

        //when
        final Optional<RacingGame> game = memoryRacingGameRepository.findById(id);

        //then
        final var gameTimeValue = game.get().getGameTimeValue();
        assertThat(gameTimeValue).isEqualTo(10);
    }

    @Test
    void findById() {
        final var optionalGame = memoryRacingGameRepository.findById(100L);
        assertThat(optionalGame.get().getGameTimeValue()).isEqualTo(10);
    }

    @Test
    void findAllCars() {
        final Map<Long, List<CarEntity>> allCars = memoryRacingGameRepository.findAllCars();
        assertThat(allCars.get(100L)).hasSize(2);
    }

    @Test
    void findAllWinners() {
        final Map<Long, List<WinnerEntity>> allWinners = memoryRacingGameRepository.findAllWinners();
        assertThat(allWinners.get(100L)).hasSize(1);
    }
}
