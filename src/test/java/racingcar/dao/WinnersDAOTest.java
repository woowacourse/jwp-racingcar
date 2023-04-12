package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class WinnersDAOTest {
    @Autowired
    private WinnersDAO winnersDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        winnersDAO = new WinnersDAO(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE winners IF EXISTS");
        jdbcTemplate.execute("create table winners\n" +
                "(\n" +
                "    game_number Integer,\n" +
                "    winner      varchar(20)" +
                ")");
    }

    @Test
    void insert() {
        assertThatNoException().isThrownBy(() -> winnersDAO.insert(5, "달리"));
    }
}
