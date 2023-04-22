package racingcar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Cars;
import racingcar.repository.dto.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class PlayerJdbcRepositoryTest {
    @Autowired
    PlayerJdbcRepository playerJdbcRepository;

    @Autowired
    RacingGameJdbcRepository racingGameJdbcRepository;

    @DisplayName("플레이어 저장")
    @Test
    void save() {
        // given
        final int savedGameId = racingGameJdbcRepository.save("저문,헤나", 10);
        final Cars cars = new Cars(List.of("저문", "헤나"));

        // when
        final int[] updatedCounts = playerJdbcRepository.saveAll(cars, savedGameId);
        final boolean isAllSaved = (updatedCounts.length == cars.getRacingCars().size());

        // then
        assertThat(isAllSaved).isTrue();
    }

    @DisplayName("레이싱 게임 id를 통해서 해당 플레이어들의 정보를 조회힌다.")
    @Test
    void findBy() {
        // given
        final int savedGameId = racingGameJdbcRepository.save("헤나,찰리", 10);
        playerJdbcRepository.saveAll(new Cars(List.of("헤나", "찰리")), savedGameId);

        // when
        final List<PlayerDto> findPlayers = playerJdbcRepository.findByRacingGameId(savedGameId);

        final List<String> findPlayerNameValues = findPlayers.stream()
                .map(PlayerDto::getName)
                .collect(Collectors.toList());

        final List<Integer> findPlayerPositions = findPlayers.stream()
                .map(PlayerDto::getPosition)
                .collect(Collectors.toList());

        // then
        assertAll(
                () -> assertThat(findPlayerNameValues).containsExactly("헤나", "찰리"),
                () -> assertThat(findPlayerPositions).containsExactly(0, 0)
        );
    }
}
