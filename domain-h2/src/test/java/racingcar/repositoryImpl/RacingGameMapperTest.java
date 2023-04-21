package racingcar.repositoryImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.GameId;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class RacingGameMapperTest {

    private static final List<Car> cars = List.of(new Car("ㅁㄴㅇㄹ", 3, 1), new Car("asdf", 4, 2));
    private static final RacingGame racingGame = new RacingGame(3, cars, 5);

    @Test
    void toGameEntity메서드를_통해_도메인_객체를_GameEntity로_바꾼다() {
        final GameEntity result = RacingGameMapper.toGameEntity(racingGame);

        assertAll(
                () -> assertThat(result.getGameId().getValue()).isEqualTo(3),
                () -> assertThat(result.getTrialCount()).isEqualTo(5)
        );
    }

    @Test
    void toCarEntities를_통해서_도메인_객체를_엔티티로_변환한다() {
        final List<CarEntity> result = RacingGameMapper.toCarEntities(cars, new GameId(3));

        assertAll(
                () -> assertThat(result).hasSize(2),
                () -> assertThat(result.get(0).getName()).isEqualTo("ㅁㄴㅇㄹ"),
                () -> assertThat(result.get(0).getPosition()).isEqualTo(3),
                () -> assertThat(result.get(0).getGameId().getValue()).isEqualTo(3),
                () -> assertThat(result.get(1).getName()).isEqualTo("asdf"),
                () -> assertThat(result.get(1).getPosition()).isEqualTo(4),
                () -> assertThat(result.get(1).getGameId().getValue()).isEqualTo(3)
        );
    }

    @Test
    void toWinnerEntity로_승리자를_엔티티로_바꾼다() {
        final List<WinnerEntity> result = RacingGameMapper.toWinnerEntity(racingGame);

        assertAll(
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.get(0).getGameId().getValue()).isEqualTo(3),
                () -> assertThat(result.get(0).getCarId().getValue()).isEqualTo(2)
        );
    }

    @Test
    void toDomain메서드로_RacingGame_객체를_만든다() {
        final RacingGame result = RacingGameMapper.toDomain(new GameEntity(3, 5),
                List.of(new CarEntity(1, "ㅁㄴㅇㄹ", 3, 3)));

        assertAll(
                () -> assertThat(result.getGameId()).isEqualTo(3),
                () -> assertThat(result.getCount().getTargetCount()).isEqualTo(5),
                () -> assertThat(result.getCars()).hasSize(1),
                () -> assertThat(result.getCars().get(0).getCarName()).isEqualTo("ㅁㄴㅇㄹ"),
                () -> assertThat(result.getCars().get(0).getPosition()).isEqualTo(3),
                () -> assertThat(result.getCars().get(0).getCarId().getValue()).isEqualTo(1));
    }

    @Test
    void toDomain_객체로_게임의_List_형태로_바군다() {
        final List<GameEntity> gameEntities = List.of(new GameEntity(1, 5), new GameEntity(2, 5));
        final List<CarEntity> carEntities = List.of(
                new CarEntity(1, "ㅁㄴㅇㄹ", 3, 1),
                new CarEntity(2, "asdf", 4, 1),
                new CarEntity(3, "ㅁㄴㅇㄹ", 3, 2),
                new CarEntity(4, "asdf", 4, 2)
        );

        final List<RacingGame> result = RacingGameMapper.toDomain(gameEntities, carEntities);

        assertAll(
                () -> assertThat(result).hasSize(2),
                () -> assertThat(result.get(0).getGameId()).isEqualTo(1),
                () -> assertThat(result.get(0).getCount().getTargetCount()).isEqualTo(5),
                () -> assertThat(result.get(0).getCars()).hasSize(2),
                () -> assertThat(result.get(0).getCars().get(0).getCarName()).isEqualTo("ㅁㄴㅇㄹ"),
                () -> assertThat(result.get(0).getCars().get(0).getPosition()).isEqualTo(3),
                () -> assertThat(result.get(0).getCars().get(0).getCarId().getValue()).isEqualTo(1),
                () -> assertThat(result.get(0).getCars().get(1).getCarName()).isEqualTo("asdf"),
                () -> assertThat(result.get(0).getCars().get(1).getPosition()).isEqualTo(4),
                () -> assertThat(result.get(0).getCars().get(1).getCarId().getValue()).isEqualTo(2),
                () -> assertThat(result.get(1).getGameId()).isEqualTo(2),
                () -> assertThat(result.get(1).getCount().getTargetCount()).isEqualTo(5),
                () -> assertThat(result.get(1).getCars()).hasSize(2),
                () -> assertThat(result.get(1).getCars().get(0).getCarName()).isEqualTo("ㅁㄴㅇㄹ"),
                () -> assertThat(result.get(1).getCars().get(0).getPosition()).isEqualTo(3),
                () -> assertThat(result.get(1).getCars().get(0).getCarId().getValue()).isEqualTo(3),
                () -> assertThat(result.get(1).getCars().get(1).getCarName()).isEqualTo("asdf"),
                () -> assertThat(result.get(1).getCars().get(1).getPosition()).isEqualTo(4),
                () -> assertThat(result.get(1).getCars().get(1).getCarId().getValue()).isEqualTo(4)
        );
    }
}
