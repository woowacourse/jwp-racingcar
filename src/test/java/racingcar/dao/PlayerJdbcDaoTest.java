package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import racingcar.dao.mapper.PlayerDtoMapper;
import racingcar.domain.CarGroup;

@Transactional
@SpringBootTest
class PlayerJdbcDaoTest {

    @Autowired
    private PlayerJdbcDao playerJdbcDao;

    @Autowired
    private RacingGameJdbcDao racingGameJdbcDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("ALTER TABLE PLAYER ALTER COLUMN id RESTART ");
        jdbcTemplate.update("ALTER TABLE RACING_GAME ALTER COLUMN id RESTART ");
    }

    @DisplayName("플레이어 저장")
    @Test
    void save() {
        // given
        CarGroup carGroup = new CarGroup("저문,헤나");
        int racingGameId = racingGameJdbcDao.save("저문,헤나", 10);

        // when
        boolean isSaved = playerJdbcDao.save(carGroup, racingGameId);

        // then
        assertThat(isSaved).isTrue();
    }

    @DisplayName("플레이어 저장 후 반환된 아이디로 조회")
    @ParameterizedTest
    @CsvSource(value = {"저문,헤나:2", "저문,디노,우가:3", "저문,디노,우가,베베:4"}, delimiter = ':')
    void findAllById(String names, int gameCount) {
        // given
        CarGroup firstCarGroup = new CarGroup(names);
        int firstRacingGameId = racingGameJdbcDao.save(names, gameCount);

        playerJdbcDao.save(firstCarGroup, firstRacingGameId);

        // when
        List<PlayerDtoMapper> firstPlayerDtoMappers = playerJdbcDao.findAllByRacingGameId(firstRacingGameId);

        // then
        assertThat(firstPlayerDtoMappers).hasSize(gameCount);
    }
}
