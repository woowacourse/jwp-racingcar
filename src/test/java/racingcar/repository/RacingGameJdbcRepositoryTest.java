package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import racingcar.repository.mapper.RacingGameInfo;

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
        final Optional<RacingGameInfo> maybeRacingGameInfo = repository.findById(savedId);

        assertTrue(maybeRacingGameInfo.isPresent());

        final RacingGameInfo racingGameInfo = maybeRacingGameInfo.get();

        // then
        assertThat(racingGameInfo)
                .hasFieldOrPropertyWithValue("id", savedId)
                .hasFieldOrPropertyWithValue("winners", "저문,헤나")
                .hasFieldOrPropertyWithValue("trial", 10);
    }
}
