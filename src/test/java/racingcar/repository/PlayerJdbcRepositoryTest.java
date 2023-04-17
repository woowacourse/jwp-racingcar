package racingcar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Cars;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        final Cars cars = new Cars("저문,헤나");

        // when
        final boolean isSaved = playerJdbcRepository.save(cars, savedGameId);

        // then
        assertThat(isSaved).isTrue();
    }
}
