package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.PlayerSaveDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@JdbcTest
@Transactional
class JdbcRacingGameRepositoryTest {

    private RacingGameRepository racingGameRepository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        racingGameRepository = new JdbcRacingGameRepository(new JdbcGameDao(jdbcTemplate), new JdbcPlayerDao(jdbcTemplate));
    }

    @Test
    @DisplayName("saveGame을 테스트를 진행한다.")
    void saveGame_whenCall_thenSuccess() {
        // given
        final PlayerSaveDto kongHana = new PlayerSaveDto("콩하나", 10, true);
        final PlayerSaveDto ethan = new PlayerSaveDto("에단", 5, false);

        // when, then
        assertThatCode(() -> racingGameRepository.save(10, List.of(kongHana, ethan)))
                .doesNotThrowAnyException();
    }
}
