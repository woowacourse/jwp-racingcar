package racingcar.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.repository.dto.RacingGameDto;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RacingGameMemoryRepositoryTest {
    RacingGameMemoryRepository racingGameRepository;

    @BeforeEach
    void setUp() {
        racingGameRepository = new RacingGameMemoryRepository();
    }

    @AfterEach
    void reset() {
        racingGameRepository.reset();
    }

    @DisplayName("게임 정보 저장 후 조회")
    @Test
    void save() {
        // given
        final String winners = "저문,헤나";
        final int trial = 10;

        // when
        final int savedId = racingGameRepository.save(winners, trial);
        final Optional<RacingGameDto> maybeRacingGameInfo = racingGameRepository.findById(savedId);

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
        final int savedGameId = racingGameRepository.save("헤나", 10);

        // when
        final List<RacingGameDto> findRacingGameDtos = racingGameRepository.findAll();
        final RacingGameDto findRacingGameDto = findRacingGameDtos.get(findRacingGameDtos.size() - 1);

        // then
        assertAll(
                () -> assertThat(findRacingGameDto.getId()).isEqualTo(savedGameId),
                () -> assertThat(findRacingGameDto.getWinners()).isEqualTo("헤나"),
                () -> assertThat(findRacingGameDto.getTrial()).isEqualTo(10)
        );
    }

    @DisplayName("RacingGame 메모리 저장시 id 동시성 문제 테스트")
    @Test
    void saveAllThreadProblem() throws Exception {
        // given & when
        saveRacingGamesByThreads("헤나,찰리", 10, 10);

        Thread.sleep(500);
        final Set<Integer> findRacingGameIds = findAllRacingGameIds();

        // then
        assertThat(findRacingGameIds).hasSize(10);
    }

    private void saveRacingGamesByThreads(final String winners, final int racingGameCount, final int trial) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(trial);
        final CountDownLatch latch = new CountDownLatch(trial);
        for (int count = 1; count <= trial; count++) {
            executorService.submit(() -> racingGameRepository.save(winners, racingGameCount));
            latch.countDown();
        }
        latch.await();
    }

    private Set<Integer> findAllRacingGameIds() {
        return racingGameRepository.findAll()
                .stream()
                .map(RacingGameDto::getId)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
