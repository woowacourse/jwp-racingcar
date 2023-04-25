package racingcar.dao.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.WinnerRepository;
import racingcar.dao.entity.WinnerEntity;

@JdbcTest
@DisplayName("WinnerJdbcRepository 테스트")
@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WinnerJdbcRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private WinnerRepository winnerRepository;

    @BeforeEach
    void setUp() {
        winnerRepository = new WinnerJdbcRepository(jdbcTemplate);
    }

    @Test
    void saveAll은_입력받은_우승자들의_정보를_모두_저장한다() {
        List<WinnerEntity> winners = List.of(new WinnerEntity(1, 1), new WinnerEntity(2, 1), new WinnerEntity(3, 1));
        winnerRepository.saveAll(winners);

        List<Integer> winnerCarIds = winnerRepository.findWinnerCarIdsByGameId(1);
        assertThat(winnerCarIds).hasSize(3);
    }

    @Test
    void findWinnerCarIdsByGameId() {
        List<WinnerEntity> winners = List.of(new WinnerEntity(1, 1), new WinnerEntity(2, 1), new WinnerEntity(3, 1));
        winnerRepository.saveAll(winners);

        List<Integer> winnerCarIds = winnerRepository.findWinnerCarIdsByGameId(1);
        assertThat(winnerCarIds).containsAll(List.of(1, 2, 3));

    }
}
