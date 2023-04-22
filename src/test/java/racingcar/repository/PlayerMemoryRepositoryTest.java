package racingcar.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Cars;
import racingcar.repository.dto.PlayerDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerMemoryRepositoryTest {
    PlayerMemoryRepository playerRepository;

    @BeforeEach
    void setUp() {
        playerRepository = new PlayerMemoryRepository();
    }

    @AfterEach
    void reset() {
        playerRepository.reset();
    }

    @DisplayName("레이싱 게임에 참여한 자동차를 저장한 후 저장된 자동차의 key값(id)를 반환한다.")
    @Test
    void saveAll() {
        // given
        final Cars cars = new Cars(List.of("헤나", "찰리"));

        // when
        final int[] updatedCarKeys = playerRepository.saveAll(cars, 1);

        // then
        assertThat(updatedCarKeys).containsExactly(1, 2);
    }

    @DisplayName("레이싱 게임 id를 통해서 플레이어(자동차) 정보를 가져온다.")
    @Test
    void findByRacingGameId() {
        // given
        final Cars cars = new Cars(List.of("헤나", "찰리"));

        // when
        playerRepository.saveAll(cars, 1);
        final List<PlayerDto> findPlayers = playerRepository.findByRacingGameId(1);

        // then
        final PlayerDto expectedPlayerHyena = new PlayerDto(1, "헤나", 0, 1);
        final PlayerDto expectedPlayerChali = new PlayerDto(2, "찰리", 0, 1);

        assertThat(findPlayers)
                .usingRecursiveComparison()
                .isEqualTo(List.of(expectedPlayerHyena, expectedPlayerChali));
    }

    @DisplayName("Player 메모리 저장시 id 동시성 문제 테스트")
    @Test
    void saveAllThreadProblem() throws Exception {
        // given
        final Cars cars = new Cars(List.of("1", "2", "3", "4", "5", "6", "7", "8"));

        // when
        saveCarsByThreads(cars, 10);

        Thread.sleep(500);
        final Set<Integer> findCarIds = findAllCarIds(10);

        // then
        assertThat(findCarIds).hasSize(80);
    }

    private void saveCarsByThreads(final Cars cars, final int trial) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(trial);
        final CountDownLatch latch = new CountDownLatch(trial);
        for (int count = 1; count <= trial; count++) {
            final int racingGameId = count;
            executorService.submit(() -> playerRepository.saveAll(cars, racingGameId));
            latch.countDown();
        }
        latch.await();
    }

    private Set<Integer> findAllCarIds(final int racingGameCounts) {
        final Set<Integer> findRacingIds = new HashSet<>();
        for (int racingGameId = 1; racingGameId <= racingGameCounts; racingGameId++) {
            findRacingIds.addAll(mapCarIds(playerRepository.findByRacingGameId(racingGameId)));
        }
        return findRacingIds;
    }

    private List<Integer> mapCarIds(final List<PlayerDto> playerDtos) {
        return playerDtos.stream()
                .map(PlayerDto::getId)
                .collect(Collectors.toList());
    }
}
