package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import racingcar.repository.mapper.RacingGameDtoMapper;

@SpringBootTest
class RacingGameRepositoryTest {

    @Autowired
    RacingGameRepository racingGameRepository;

    @DisplayName("게임 정보 저장 후 조회")
    @Test
    void save() {
        // given
        final String winners = "저문,헤나";
        final int trial = 10;

        // when
        final int savedId = racingGameRepository.save(winners, trial);
        final Optional<RacingGameDtoMapper> maybeRacingGameInfo = racingGameRepository.findById(savedId);

        assertTrue(maybeRacingGameInfo.isPresent());

        final RacingGameDtoMapper racingGameDtoMapper = maybeRacingGameInfo.get();

        // then
        assertThat(racingGameDtoMapper)
                .hasFieldOrPropertyWithValue("id", savedId)
                .hasFieldOrPropertyWithValue("winners", winners)
                .hasFieldOrPropertyWithValue("trial", trial);
    }

    @DisplayName("저장된 항목에 대한 전체 조회")
    @Test
    void findAll() {
        // given
        racingGameRepository.save("저문,헤나", 10);
        racingGameRepository.save("저문,디노,우가", 10);
        racingGameRepository.save("저문,디노,베베,우가", 10);

        // when
        List<RacingGameDtoMapper> racingGameDtoMappers = racingGameRepository.findAll();

        //then
        assertThat(racingGameDtoMappers.size()).isEqualTo(3);
    }
}
