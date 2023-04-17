package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class WinnersDaoTest {

    @Autowired
    private WinnersDao winnersDao;

    @Test
    void insert() {
        assertThatNoException().isThrownBy(() -> winnersDao.insert(5, "달리"));
    }

    @Test
    void find() {
        winnersDao.insert(2, "달리");
        winnersDao.insert(2, "디노");
        assertThat(winnersDao.find(2)).isEqualTo(List.of("달리", "디노"));
    }
}
