package racingcar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.domain.Position;
import racingcar.repository.mapper.PlayerMapper;

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
        final boolean isSaved = playerJdbcRepository.save(cars, savedGameId);

        // then
        assertThat(isSaved).isTrue();
    }

    @DisplayName("레이싱 게임 id를 통해서 해당 플레이어들의 정보를 조회힌다.")
    @Test
    void findBy() {
        // given
        final int savedGameId = racingGameJdbcRepository.save("헤나,찰리", 10);
        playerJdbcRepository.save(new Cars(List.of("헤나", "찰리")), savedGameId);

        // when
        final List<PlayerMapper> findPlayerMappers = playerJdbcRepository.findBy(savedGameId);

        final List<String> findPlayerNameValues = findPlayerMappers.stream()
                .map(PlayerMapper::getName)
                .map(Name::getValue)
                .collect(Collectors.toList());

        final List<Position> findPlayerPositions = findPlayerMappers.stream()
                .map(PlayerMapper::getPosition)
                .collect(Collectors.toList());

        // then
        assertAll(
                () -> assertThat(findPlayerNameValues).containsExactly("헤나", "찰리"),
                () -> assertThat(findPlayerPositions).containsExactly(Position.ZERO, Position.ZERO)
        );
    }
}
