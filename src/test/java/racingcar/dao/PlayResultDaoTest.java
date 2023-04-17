package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@JdbcTest
class PlayResultDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private PlayResultDao playResultDao;

    @BeforeAll
    void setUp() {
        playResultDao = new PlayResultDao(jdbcTemplate);
    }

    @DisplayName("게임 결과가 저장되면 id를 반환하는 기능 테스트")
    @Test
    @Transactional
    void Should_ReturnGameId_When_InsertPlayResult() {
        Long gameId = playResultDao.insertWithKeyHolder(10, List.of("tori", "hong"));
        assertThat(gameId).isNotNull();
    }
}
