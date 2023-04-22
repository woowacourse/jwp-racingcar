package racingcar.dao;

import org.assertj.core.api.Assertions;
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
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("transactional")
class PlayResultDAOTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PlayResultDAO playResultDAO;

    @BeforeEach
    void setUp() {
        playResultDAO = new PlayResultDAO(dataSource, jdbcTemplate);
    }

    @Test
    void returnTableIdAfterInsert_는_값을_저장한_후_생성된_키를_반환한다() {
        //given
        assertThatThrownBy(()-> jdbcTemplate.queryForObject("select id from play_result", Integer.class))
                .isInstanceOf(EmptyResultDataAccessException.class);

        //when
        final int generatedKey = playResultDAO.returnTableIdAfterInsert(
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
        playResultDAO.returnTableIdAfterInsert(2, List.of(new Car("a"), new Car("b")));
        playResultDAO.returnTableIdAfterInsert(2, List.of(new Car("a"), new Car("b")));

        //when
        final List<Integer> existingRowsId = jdbcTemplate.queryForList("select id from play_result", Integer.class);

        //then
        assertThat(existingRowsId).hasSize(2);
    }

}
