package racing.dao.game;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import racing.domain.Game;

@Sql("/resetTable.sql")
@JdbcTest
class H2GameDaoTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    void insert() {
        final H2GameDao h2GameDao = new H2GameDao(namedParameterJdbcTemplate);
        h2GameDao.insert(new Game("6"));
        final List<Integer> allId = h2GameDao.getAllId();
        Assertions.assertThat(allId).containsExactly(1);
    }

    @Test
    void getAllId() {
        final H2GameDao h2GameDao = new H2GameDao(namedParameterJdbcTemplate);
        h2GameDao.insert(new Game("6"));
        h2GameDao.insert(new Game("6"));
        h2GameDao.insert(new Game("6"));
        h2GameDao.insert(new Game("6"));
        final List<Integer> allId = h2GameDao.getAllId();
        Assertions.assertThat(allId).containsExactly(1, 2, 3, 4);
    }
}
