package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.TryCount;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameResultDaoTest {


    @Autowired
    private GameResultDao gameResultDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //    @BeforeEach
//    void setUp() {
//        gameResultDao = new GameResultDao(jdbcTemplate);
//        jdbcTemplate.execute("DROP TABLE game_result IF EXISTS");
//        jdbcTemplate.execute("CREATE TABLE game_result(" +
//                "id SERIAL, try_count INT");
//
//        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
//                .map(name -> name.split(" "))
//                .collect(Collectors.toList());
//
//        List<Integer> collect = Arrays.asList(1, 2, 3).stream()
//                .collect(Collectors.toList());
//        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
//    }
    @Test
    void save() {
        Long id = gameResultDao.insert(new TryCount(3));
        Assertions.assertThat(1L).isEqualTo(id);
    }

}
