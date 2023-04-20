package racing.dao.game;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import racing.domain.Game;
import racing.dto.GameDto;

@Sql("/resetTable.sql")
@JdbcTest
class H2GameDaoTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @DisplayName("총 횟수를 이용하여 게임 데이터를 삽입한다.")
    @Test
    void insert() {
        //given
        final H2GameDao h2GameDao = new H2GameDao(namedParameterJdbcTemplate);
        h2GameDao.insert(new Game("6"));

        //when
        final List<GameDto> games = namedParameterJdbcTemplate.query(
            "SELECT * FROM GAME",
            (rs, rowNum) -> {
                final int count = rs.getInt("play_count");
                return new GameDto(count);
            });
        
        //then
        Assertions.assertThat(games).hasSize(1);
        Assertions.assertThat(games.get(0).getCount()).isEqualTo(6);
    }

    @DisplayName("데이터에 존재하는 모든 아이디를 조회한다.")
    @Test
    void getAllId() {
        //given
        final H2GameDao h2GameDao = new H2GameDao(namedParameterJdbcTemplate);
        h2GameDao.insert(new Game("6"));
        h2GameDao.insert(new Game("6"));

        //when
        final List<Integer> allId = h2GameDao.getAllId();

        //then
        Assertions.assertThat(allId).containsExactly(1, 2);
    }
}
