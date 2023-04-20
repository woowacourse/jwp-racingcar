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
class H2GameDaoTest {
    private H2GameDao h2PlayResultDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcTemplate.update("DELETE FROM game");
        h2PlayResultDao = new H2GameDao(jdbcTemplate);
    }

    @DisplayName("게임 결과가 저장되면 id를 반환하는 기능 테스트")
    @Test
    void Should_ReturnGameId_When_InsertPlayResult() {
        Long gameId = h2PlayResultDao.insertWithKeyHolder(10, List.of("tori", "hong"));
        assertThat(gameId).isEqualTo(1);
    }
}
