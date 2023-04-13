package racingcar.dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Car;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InsertDaoTest {

    @Autowired
    private InsertDao insertDao;

    @Test
    void insert() {
        Assertions.assertThatNoException()
                .isThrownBy(() ->
                        insertDao.insert("jena", 3, List.of(new Car("jena", 1), new Car("odo", 2))));
    }
}
