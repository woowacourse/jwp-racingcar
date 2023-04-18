package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class ResultDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("결과 추가 시 id가 반환되는지 확인")
    void insert() {

        ResultDao resultDao = new ResultDao(jdbcTemplate);

        long id = resultDao.insert(4, "roy");

        assertThat(id).isEqualTo(1);
    }
}
