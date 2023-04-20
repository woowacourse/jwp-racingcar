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

    @DisplayName("게임 결과가 저장되면 id를 반환할 수 있다.")
    @Test
    void Should_ReturnGameId_When_InsertPlayResult() {
        Long gameId = h2PlayResultDao.insert(10, List.of("토리", "홍실"));

        assertThat(gameId).isNotNull();
    }

    @DisplayName("전체 게임 결과를 반환할 수 있다.")
    @Test
    void Should_ReturnAllResult_When_SelectAll() {
        h2PlayResultDao.insert(10, List.of("토리", "말랑"));
        h2PlayResultDao.insert(5, List.of("토리", "말랑"));

        assertThat(h2PlayResultDao.selectAll()).hasSize(2);
    }
}
