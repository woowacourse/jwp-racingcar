package racingcar.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.dao.dto.GameIdDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class LocalGameDaoTest {

    private final GameDao gameDao = new LocalGameDao();

    @Test
    void 게임을_저장한다() {
        // given
        final int trialCount = 5;

        // when
        final Long id = gameDao.insert(trialCount);

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
        final List<GameIdDTO> allGameIdDTOs = gameDao.findAllGameIds();

        //then
        assertSoftly(softly -> {
            softly.assertThat(allGameIdDTOs).hasSize(2);
            softly.assertThat(allGameIdDTOs.get(0).getId()).isEqualTo(firstId);
            softly.assertThat(allGameIdDTOs.get(1).getId()).isEqualTo(secondId);
        });
    }

    @AfterEach
    void tearDown() {
        gameDao.deleteAll();
    }
}
