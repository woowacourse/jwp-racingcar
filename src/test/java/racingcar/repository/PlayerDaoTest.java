package racingcar.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource(locations = "/application.properties")
@JdbcTest
class PlayerDaoTest {

    private PlayerDao playerDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    void clear() {
        jdbcTemplate.execute("DROP TABLE player");
    }

    @BeforeEach
    void setUp() {
        playerDao = new PlayerDao(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE player IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE player (\n" +
                "    name varchar(30) PRIMARY KEY\n" +
                ");");

        List<Object[]> names = Stream.of(new String[]{"doggy"}, new String[]{"power"})
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO player(name) VALUES (?)", names);
    }

    @Test
    @DisplayName("insert시 중복이 없으면 예외가 발생하지 않는다")
    void insert시_중복이_없으면_예외가_발생하지_않는다() {
        assertThatNoException().isThrownBy(
                () -> playerDao.insert("test")
        );
    }

    @Test
    @DisplayName("insert시 중복 이름이 있는 경우 예외가 발생한다")
    void insert시_중복_이름이_있는_경우_예외가_발생한다() {
        assertThatThrownBy(
                () -> playerDao.insert("doggy")
        ).isInstanceOf(DuplicateKeyException.class);
    }
}
