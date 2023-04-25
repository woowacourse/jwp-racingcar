package racingcar.dao.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.GameRepository;
import racingcar.dao.entity.GameEntity;


@JdbcTest
@DisplayName("GameJdbcRepository 테스트")
@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameJdbcRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private GameRepository gameRepository;

  @BeforeEach
  void setUp() {
    gameRepository = new GameJdbcRepository(jdbcTemplate);
  }

  @Test
  void save는_입력받은_Game_정보를_저장한다() {
    int gameId = gameRepository.save(new GameEntity(null, 3, LocalTime.now()));

    assertThat(gameRepository.findGameIds()).hasSize(1);
    assertThat(gameRepository.findGameIds()).contains(gameId);
  }

  @Test
  void findGameIds는_저장된_게임의_id를_모두_불러온다() {
    int gameId1 = gameRepository.save(new GameEntity(null, 3, LocalTime.now()));
    int gameId2 = gameRepository.save(new GameEntity(null, 3, LocalTime.now()));
    int gameId3 = gameRepository.save(new GameEntity(null, 3, LocalTime.now()));

    assertThat(gameRepository.findGameIds()).hasSize(3);
    assertThat(gameRepository.findGameIds()).containsAll(List.of(gameId1, gameId2, gameId3));
  }
}
