package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.PlayerEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlayerDaoTest {

    private final PlayerDao playerDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerDaoTest(final PlayerDao playerDao, final JdbcTemplate jdbcTemplate) {
        this.playerDao = playerDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @DisplayName("이름을 입력받아 저장한다.")
    @ParameterizedTest(name = "name: {0}")
    @ValueSource(strings = {"루카", "망고", "소니", "현구막"})
    void save(final String name) {
        //when
        Long id = playerDao.save(name);
        //then
        String sql = "SELECT name FROM PLAYER WHERE id = ?";
        assertThat(jdbcTemplate.queryForObject(sql, String.class, id)).isEqualTo(name);
    }

    @DisplayName("이름을 입력받아 조회한다.")
    @Test
    void findByName() {
        //given
        String name = "포비";
        playerDao.save(name);
        //when
        PlayerEntity playerEntity = playerDao.findByName(name).orElseThrow();
        //then
        assertThat(playerEntity.getId()).isNotNull();
        assertThat(playerEntity.getName()).isEqualTo(name);
    }


    @DisplayName("이름을 입력받아 조회한 결과가 없을 때, empty를 반환한다.")
    @Test
    void findByNameWhenEmpty() {
        //given
        String name = "네오";
        //when
        Optional<PlayerEntity> playerDto = playerDao.findByName(name);
        //then
        assertThat(playerDto).isEmpty();
    }
}
