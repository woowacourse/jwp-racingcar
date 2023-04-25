package racingcar.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.controller.ApplicationType;
import racingcar.entity.GameEntity;
import racingcar.entity.PlayerEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Transactional
class JdbcPlayerDaoTestEntity {

    private PlayerDao playerDao;
    private GameDao gameDao;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        playerDao = new JdbcPlayerDao(jdbcTemplate);
        gameDao = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    @DisplayName("Player 테이블에 값을 저장할 수 있다.")
    void savePlayer_whenCall_thenSuccess() {
        // given
        Long gameId = gameDao.save(new GameEntity(10, ApplicationType.CONSOLE));
        final PlayerEntity kongHana = new PlayerEntity("콩하나", 10, true);
        final PlayerEntity ethan = new PlayerEntity("에단", 5, false);

        // when, then
        assertThatCode(() -> playerDao.save(gameId, List.of(kongHana, ethan)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Game 테이블에 저장되지 않은 gameId를 사용하면 Player 테이블에 값을 저장할 수 없다.")
    void savePlayer_whenInvalidGameId_thenFail() {
        // given
        final PlayerEntity kongHana = new PlayerEntity("콩하나", 10, true);
        final PlayerEntity ethan = new PlayerEntity("에단", 5, false);

        // when, then
        assertThatThrownBy(() -> playerDao.save(2, List.of(kongHana, ethan)))
                .isInstanceOf(DataAccessException.class);
    }

    @Test
    @DisplayName("player 테이블에 저장된 정보를 불러올 수 있다.")
    void findPlayer_whenCall_thenSuccess() {
        // given
        String konghanaName = "콩하나";
        String ethanName = "에단";

        // when
        Long gameId = gameDao.save(new GameEntity(10, ApplicationType.CONSOLE));
        final PlayerEntity kongHana = new PlayerEntity(konghanaName, 10, true);
        final PlayerEntity ethan = new PlayerEntity(ethanName, 5, false);
        playerDao.save(gameId, List.of(kongHana, ethan));

        List<PlayerEntity> playerEntity = playerDao.findById(gameId);

        // then
        Assertions.assertAll(
                () -> assertThat(playerEntity.size()).isEqualTo(2),
                () -> assertThat(playerEntity.get(0).getName()).isEqualTo(konghanaName),
                () -> assertThat(playerEntity.get(0).getPosition()).isEqualTo(10),
                () -> assertThat(playerEntity.get(0).getIsWinner()).isEqualTo(true),
                () -> assertThat(playerEntity.get(1).getName()).isEqualTo(ethanName),
                () -> assertThat(playerEntity.get(1).getPosition()).isEqualTo(5),
                () -> assertThat(playerEntity.get(1).getIsWinner()).isEqualTo(false)
        );
    }

    @Test
    @DisplayName("Game 테이블에 저장되지 않은 gameId를 사용하면 비어있는 리스트가 반환된다.")
    void findPlayer_whenInvalidGameId_thenFail() {
        // given, when, then
        assertThat(playerDao.findById(2)).isEmpty();
    }
}
