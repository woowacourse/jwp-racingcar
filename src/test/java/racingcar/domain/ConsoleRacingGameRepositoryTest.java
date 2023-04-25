package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ConsoleRacingGameRepositoryTest {

    private ConsoleRacingGameRepository racingGameRepository = new ConsoleRacingGameRepository();

    @DisplayName("게임을 저장한다.")
    @Test
    void save() {
        final RacingGame racingGame = new RacingGame(new Cars(new String[]{"a", "b"}), new GameTime(10));
        final Long id = racingGameRepository.save(racingGame);

        final Map<Long, RacingGame> racingGameSaved = racingGameRepository.getRacingGameSaved();
        final RacingGame gameSaved = racingGameSaved.get(id);

        assertThat(gameSaved.getCars()).hasSize(2);
    }

    @DisplayName("게임을 조회한다.")
    @Test
    void findById() {
        final RacingGame racingGame = new RacingGame(new Cars(new String[]{"a", "b"}), new GameTime(10));
        final Long id = racingGameRepository.save(racingGame);

        final Optional<RacingGameDto> dtoById = racingGameRepository.findById(id);
        final int trialCount = dtoById.get().getTrialCount();

        assertThat(trialCount).isEqualTo(10);
    }

    @DisplayName("모든 차를 조회한다.")
    @Test
    void findAllCars() {
        //given
        final RacingGame racingGame = new RacingGame(new Cars(new String[]{"a", "b"}), new GameTime(10));
        final Long id = racingGameRepository.save(racingGame);

        final RacingGame racingGame2 = new RacingGame(new Cars(new String[]{"a", "b", "c"}), new GameTime(10));
        final Long id2 = racingGameRepository.save(racingGame2);

        //when
        final Map<Long, List<CarDto>> allCars = racingGameRepository.findAllCars();
        final List<CarDto> carDtos = allCars.get(id);
        final List<CarDto> carDtos2 = allCars.get(id2);

        //then
        assertAll(
                () -> assertThat(carDtos).hasSize(2),
                () -> assertThat(carDtos2).hasSize(3)
        );
    }

    @DisplayName("모든 승자를 조회한다.")
    @Test
    void findAllWinners() {
        //given
        final List<Car> carList = List.of(new Car("a", 3), new Car("b", 4));
        final RacingGame racingGame = new RacingGame(new Cars(carList), new GameTime(10));
        final Long id = racingGameRepository.save(racingGame);

        final Map<Long, Winners> winnersSaved = racingGameRepository.getWinnersSaved();
        final Winners winners = winnersSaved.get(id);

        assertThat(winners.getWinners()).hasSize(1);
    }
}
