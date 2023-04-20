package racingcar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dto.RacingGameDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RacingGameJdbcRepositoryTest {

    @Autowired
    RacingGameJdbcRepository repository;

    @DisplayName("게임 정보 저장 후 조회")
    @Test
    void save() {
        // given
        final String winners = "저문,헤나";
        final int trial = 10;

        // when
        final int savedId = repository.save(winners, trial);
        final Optional<RacingGameDto> maybeRacingGameInfo = repository.findById(savedId);

        assertTrue(maybeRacingGameInfo.isPresent());

        final RacingGameDto racingGameDto = maybeRacingGameInfo.get();

        // then
        assertThat(racingGameDto)
                .hasFieldOrPropertyWithValue("id", savedId)
                .hasFieldOrPropertyWithValue("winners", "저문,헤나")
                .hasFieldOrPropertyWithValue("trial", 10);
    }

    @DisplayName("여러 게임 정보를 조회한다.")
    @Test
    void findAll() {
        // given
        final int savedGameId = repository.save("헤나", 10);

        // when
        final List<RacingGameDto> findRacingGameDtos = repository.findAll();
        final RacingGameDto findRacingGameDto = findRacingGameDtos.get(findRacingGameDtos.size() - 1);

        // then
        assertAll(
                () -> assertThat(findRacingGameDto.getId()).isEqualTo(savedGameId),
                () -> assertThat(findRacingGameDto.getWinners()).isEqualTo("헤나"),
                () -> assertThat(findRacingGameDto.getTrial()).isEqualTo(10)
        );
    }
}
