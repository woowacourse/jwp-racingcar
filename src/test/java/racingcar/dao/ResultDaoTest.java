package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JdbcTest
class ResultDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private ResultDao resultDao;

    @BeforeEach
    void init() {
        resultDao = new ResultDao(jdbcTemplate);

        resultDao.insert(1, "test1");
        resultDao.insert(2, "test2");
        resultDao.insert(3, "test3");
        resultDao.insert(4, "test4");
    }

    @Test
    @DisplayName("결과 추가 시 id가 반환되는지 확인")
    void insert() {
        long id = resultDao.insert(8, "roy");

        assertThat(id).isEqualTo(5);
    }

    @Test
    @DisplayName("저장되어 있는 결과 id 리스트가 제대로 반환되는지 확인")
    void findAllId() {
        List<Long> ids = resultDao.findAllId();

        assertThat(ids).containsExactly(1L, 2L, 3L, 4L);
    }

    @Test
    @DisplayName("아이디에 따른 결과 객체가 제대로 반환되는지 확인")
    void findBy() {
        String winners = resultDao.findWinnerBy(1);

        assertThat(winners).isEqualTo("test1");
    }
}
