package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class PlayResultDaoTest {
    private PlayResultDao playResultDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcTemplate.update("DELETE FROM PLAY_RESULT");
        playResultDao = new PlayResultDao(jdbcTemplate);
    }

    @DisplayName("게임 결과가 저장되면 id를 반환하는 기능 테스트")
    @Test
    void Should_ReturnGameId_When_InsertPlayResult() {
        Long gameId = playResultDao.insertWithKeyHolder(10, List.of("tori", "hong"));
        assertThat(gameId).isEqualTo(1);
    }
}
