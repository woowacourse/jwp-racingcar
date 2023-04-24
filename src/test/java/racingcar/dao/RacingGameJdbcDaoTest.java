package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import racingcar.dao.dto.RacingGameDto;

@Transactional
@SpringBootTest
class RacingGameJdbcDaoTest {

    @Autowired
    private RacingGameJdbcDao racingGameJdbcDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("ALTER TABLE PLAYER ALTER COLUMN id RESTART ");
        jdbcTemplate.update("ALTER TABLE RACING_GAME ALTER COLUMN id RESTART ");
    }

    @DisplayName("게임 정보 저장 후 조회")
    @Test
    void save() {
        // given
        final String winners = "저문,헤나";
        final int trial = 10;

        // when
        final int savedId = racingGameJdbcDao.save(winners, trial);
        final Optional<RacingGameDto> maybeRacingGameInfo = racingGameJdbcDao.findById(savedId);

        assertTrue(maybeRacingGameInfo.isPresent());

        final RacingGameDto racingGameDto = maybeRacingGameInfo.get();

        // then
        assertThat(racingGameDto)
                .hasFieldOrPropertyWithValue("id", savedId)
                .hasFieldOrPropertyWithValue("winners", winners);
    }

    @DisplayName("저장된 항목에 대한 전체 조회")
    @Test
    void findAll() {
        // given
        racingGameJdbcDao.save("저문,헤나", 10);
        racingGameJdbcDao.save("저문,디노,우가", 10);
        racingGameJdbcDao.save("저문,디노,베베,우가", 10);

        // when
        List<RacingGameDto> racingGameDtos = racingGameJdbcDao.findAll();

        //then
        assertThat(racingGameDtos).hasSize(3);
    }
}
