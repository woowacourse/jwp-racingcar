package racingcar.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.repository.mapper.RacingGameMapper;

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
        final Optional<RacingGameMapper> maybeRacingGameInfo = repository.findById(savedId);

        assertTrue(maybeRacingGameInfo.isPresent());

        final RacingGameMapper racingGameMapper = maybeRacingGameInfo.get();

        // then
        assertThat(racingGameMapper)
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
        final List<RacingGameMapper> findRacingGameMappers = repository.findAll();
        final RacingGameMapper findRacingGameMapper = findRacingGameMappers.get(0);

        // then
        assertAll(
                () -> Assertions.assertThat(findRacingGameMapper.getId()).isEqualTo(savedGameId),
                () -> Assertions.assertThat(findRacingGameMapper.getWinners()).isEqualTo("헤나"),
                () -> Assertions.assertThat(findRacingGameMapper.getTrial()).isEqualTo(10)
        );
    }
}
