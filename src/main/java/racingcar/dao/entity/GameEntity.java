package racingcar.dao.entity;

import racingcar.domain.RacingGame;

public class GameEntity {

    private final Integer gameId;
    private final RacingGame racingGame;

    public GameEntity(final Integer gameId, final RacingGame racingGame) {
        this.gameId = gameId;
        this.racingGame = racingGame;
    }

    public GameEntity(final Integer gameId, final GameEntity gameEntity) {
        this.gameId = gameId;
        racingGame = gameEntity.racingGame;
    }

    public static GameEntity fromDomain(final RacingGame racingGame) {
        return new GameEntity(null, racingGame);
    }

    public int getGameId() {
        return gameId;
    }

    public RacingGame getRacingGame() {
        return racingGame;
    }

    public int getCount() {
        return racingGame.getCount().getTargetCount();
    }
}
