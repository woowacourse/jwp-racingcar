package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import racingcar.dao.gameresult.GameResultDaoWebImpl;
import racingcar.dto.db.GameResultDto;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Sql("/data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameResultDaoWebImplTest {

    @Autowired
    private GameResultDaoWebImpl gameResultDaoWebImpl;

    @Test
    void save1() {
        Long id = gameResultDaoWebImpl.save(new GameResultDto(3));
        assertThat(1L).isEqualTo(id);
    }

    @Test
    void save2() {
        Long id = gameResultDaoWebImpl.save(new GameResultDto(3));
        assertThat(1L).isEqualTo(id);
    }
}
