package racingcar.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcGameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    void 게임을_저장한다() {
        // given
        final int trialCount = 5;

        // when
        final long id = gameDao.insert(trialCount);

        // then
        assertThat(id).isNotNull();
    }

    @Test
    void 데이터_행의_개수를_센다() {
        //given
        gameDao.insert(5);

        //when
        final int rowCount = gameDao.countRows();

        //then
        assertThat(rowCount).isEqualTo(1);
    }

    @Test
    void 모든_데이터를_삭제한다() {
        //given
        gameDao.insert(5);

        //when
        gameDao.deleteAll();
        final int rowCount = gameDao.countRows();

        //then
        assertThat(rowCount).isEqualTo(0);
    }

    @Test
    void 모든_게임_아이디들을_찾는다() {
        //given
        final Long firstId = gameDao.insert(5);
        final Long secondId = gameDao.insert(5);

        //when
        final List<GameIdDTO> allGameIdDTOS = gameDao.findAllGameIds();

        //then
        assertThat(allGameIdDTOS).hasSize(2).containsExactly(new GameIdDTO(firstId), new GameIdDTO(secondId));
        System.out.println(allGameIdDTOS);
    }

    @AfterEach
    void tearDown() {
        gameDao.deleteAll();
    }
}
