package racingcar.dao.entity;

import racingcar.domain.RacingGame;

public class InsertGameEntity {

    private final Integer gameId;
    private final RacingGame racingGame;

    public InsertGameEntity(final Integer gameId, final RacingGame racingGame) {
        this.gameId = gameId;
        this.racingGame = racingGame;
    }

    public InsertGameEntity(final Integer gameId, final InsertGameEntity insertGameEntity) {
        this.gameId = gameId;
        racingGame = insertGameEntity.racingGame;
    }

    public static InsertGameEntity fromDomain(final RacingGame racingGame) {
        return new InsertGameEntity(null, racingGame);
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
