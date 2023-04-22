package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import racingcar.domain.Car;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("transactional")
class JdbcPlayResultDAOTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcPlayResultDAO jdbcPlayResultDAO;

    @BeforeEach
    void setUp() {
        jdbcPlayResultDAO = new JdbcPlayResultDAO(dataSource, jdbcTemplate);
    }

    @Test
    void returnTableIdAfterInsert_는_값을_저장한_후_생성된_키를_반환한다() {
        //given
        assertThatThrownBy(()-> jdbcTemplate.queryForObject("select id from play_result", Integer.class))
                .isInstanceOf(EmptyResultDataAccessException.class);

        //when
        final int generatedKey = jdbcPlayResultDAO.returnTableIdAfterInsert(
                2,
                List.of(new Car("a"), new Car("b"))
        );
        final Integer actual = jdbcTemplate.queryForObject("select id from play_result", Integer.class);

        //then
        assertThat(generatedKey).isEqualTo(actual);
    }

    @Test
    void findAll_는_모든_저장된_정보를_가져온다() {
        //given
        jdbcPlayResultDAO.returnTableIdAfterInsert(2, List.of(new Car("a"), new Car("b")));
        jdbcPlayResultDAO.returnTableIdAfterInsert(2, List.of(new Car("a"), new Car("b")));

        //when
        final List<Integer> existingRowsId = jdbcTemplate.queryForList("select id from play_result", Integer.class);

        //then
        assertThat(existingRowsId).hasSize(2);
    }

}
