package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ResultDaoTest {

    @Autowired
    ResultDao resultDao;

    @Test
    @DisplayName("결과 추가 시 id가 반환되는지 확인")
    void insert() {
        long id = resultDao.insert(4, "roy");

        assertThat(id).isEqualTo(1);
    }
}
