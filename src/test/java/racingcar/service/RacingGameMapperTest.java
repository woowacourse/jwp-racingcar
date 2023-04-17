package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.utils.TestNumberGenerator;

@DisplayName("RacingGameMapper 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class RacingGameMapperTest {

    private final RacingGameMapper racingGameMapper = new RacingGameMapper();

    @Test
    void toGameEntity_메서드는_시도_횟수를_입력받아_게임_엔티티를_반환한다() {
        // given
        final int trial = 5;

        // when
        final GameEntity gameEntity = racingGameMapper.toGameEntity(trial);

        // then
        assertThat(gameEntity.getTrial()).isEqualTo(5);
    }

    @Test
    void toCarEntities_메서드는_RacingGame과_gameId를_받아_자동차_엔티티들을_반환한다() {
        // given
        final TestNumberGenerator numberGenerator = new TestNumberGenerator(List.of(4, 3));
        final RacingGame racingGame = new RacingGame(numberGenerator, List.of("비버", "허브"), 1);
        racingGame.play();
        final int gameId = 1;

        // when
        final List<CarEntity> carEntities = racingGameMapper.toCarEntities(racingGame, gameId);

        // then
        assertAll(
                () -> assertThat(carEntities).hasSize(2),
                () -> assertThat(carEntities.get(0).getName()).isEqualTo("비버"),
                () -> assertThat(carEntities.get(0).getPosition()).isEqualTo(1)
        );
    }
}
