package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InMemoryGameDaoTest {
    private InMemoryGameDao inMemoryGameDao;

    @BeforeEach
    void setUp() {
        inMemoryGameDao = new InMemoryGameDao();
    }

    @DisplayName("게임 결과가 저장되면 id를 반환할 수 있다.")
    @Test
    void Should_ReturnGameId_When_InsertPlayResult() {
        Long gameId = inMemoryGameDao.insert(10, List.of("토리", "홍실"));

        assertThat(gameId).isEqualTo(1);
    }

    @DisplayName("전체 게임 결과를 반환할 수 있다.")
    @Test
    void Should_ReturnAllResult_When_SelectAll() {
        inMemoryGameDao.insert(10, List.of("토리", "말랑"));
        inMemoryGameDao.insert(5, List.of("토리", "말랑"));

        assertThat(inMemoryGameDao.selectAll()).hasSize(2);
    }
}
