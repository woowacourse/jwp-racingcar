package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.entity.Player;
import racingcar.dto.RacingGameFindDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
    @DisplayName("자동차 경주를 테이블에 저장할 수 있다.")
    void save_whenCall_thenSuccess() {
        // given
        final Player kongHana = new Player("콩하나", 10, true);
        final Player ethan = new Player("에단", 5, false);

        // when, then
        assertThatCode(() -> racingGameRepository.save(10, List.of(kongHana, ethan)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("저장된 자동차 경주를 조회할 수 있다.")
    void find_whenCall_thenSuccess() {
        // given
        final Player kongHana = new Player("콩하나", 10, true);
        final Player ethan = new Player("에단", 5, false);
        Long gameId = racingGameRepository.save(10, List.of(kongHana, ethan));

        // when, then
        List<RacingGameFindDto> oneGameHistoryDtos = racingGameRepository.findAll();
        assertAll(
                () -> assertThat(oneGameHistoryDtos.size()).isEqualTo(1),
                () -> assertThat(oneGameHistoryDtos.get(0).getGameFindDto().getId()).isEqualTo(gameId),
                () -> assertThat(oneGameHistoryDtos.get(0).getPlayerFindDtos().size()).isEqualTo(2),
                () -> assertThat(oneGameHistoryDtos.get(0).getPlayerFindDtos().get(0).getName()).isEqualTo("콩하나"),
                () -> assertThat(oneGameHistoryDtos.get(0).getPlayerFindDtos().get(0).getPosition()).isEqualTo(10),
                () -> assertThat(oneGameHistoryDtos.get(0).getPlayerFindDtos().get(0).getIsWinner()).isTrue(),
                () -> assertThat(oneGameHistoryDtos.get(0).getPlayerFindDtos().get(1).getName()).isEqualTo("에단"),
                () -> assertThat(oneGameHistoryDtos.get(0).getPlayerFindDtos().get(1).getPosition()).isEqualTo(5),
                () -> assertThat(oneGameHistoryDtos.get(0).getPlayerFindDtos().get(1).getIsWinner()).isFalse()
        );
    }

    @Test
    @DisplayName("팩토리 메서드를 사용할 수 있다.")
    void generateDefaultJdbcRacingGameRepository_whenCall_thenSuccess() {
        assertDoesNotThrow(JdbcRacingGameRepository::generateDefaultJdbcRacingGameRepository);
    }
}
