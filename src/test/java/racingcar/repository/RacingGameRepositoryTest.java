package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RacingGameRepositoryTest {

    @Autowired
    RacingGameRepository repository;

    @DisplayName("게임 정보 저장")
    @Test
    void save() {
        // given
        final String winners = "저문,헤나";
        final int trial = 10;

        // when
        final int savedId = repository.save(winners, trial);

        // then
        assertThat(savedId).isEqualTo(1);
    }
}
