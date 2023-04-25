package racingcar.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.RacingGame;

class InMemoryRacingRepositoryTest {

    private final InMemoryRacingRepository inMemoryRacingRepository = new InMemoryRacingRepository();

    @DisplayName("게임을 저장할 수 있고, 반환 시 저장한 게임이 반환된다.")
    @Test
    void shouldReturnRacingGameAsSaveAsWhenAfterSave() {
        RacingGame racingGame1 = RacingGame.of(List.of("브리", "브라운", "토미"), 3);
        RacingGame racingGame2 = RacingGame.of(List.of("브라운", "브리"), 5);
        this.inMemoryRacingRepository.saveGameResult(racingGame1, 3);
        this.inMemoryRacingRepository.saveGameResult(racingGame2, 5);

        List<RacingGame> racingGames = this.inMemoryRacingRepository.findAllRacingGames();

        assertAll(
                () -> assertThat(racingGames.get(0)).isEqualTo(racingGame1),
                () -> assertThat(racingGames.get(1)).isEqualTo(racingGame2)
        );
    }
}
