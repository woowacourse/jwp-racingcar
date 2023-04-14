package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class RacingGameDaoTest {

    @Autowired
    private DataSource dataSource;
    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameDao(dataSource);
    }

    @DisplayName("자동차 게임 정보를 저장한다.")
    @Test
    void save() {
        final Long id = racingGameDao.save("루쿠", 10);

        assertThat(id).isNotNull();
    }
}
