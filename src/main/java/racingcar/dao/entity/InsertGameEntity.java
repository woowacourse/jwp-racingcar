package racingcar.dao.entity;

import racingcar.domain.RacingGameResult;

public class InsertGameEntity {

    private final Integer gameId;
    private final RacingGameResult racingGameResult;

    public InsertGameEntity(final Integer gameId, final RacingGameResult racingGameResult) {
        this.gameId = gameId;
        this.racingGameResult = racingGameResult;
    }

    public InsertGameEntity(final Integer gameId, final InsertGameEntity insertGameEntity) {
        this.gameId = gameId;
        racingGameResult = insertGameEntity.racingGameResult;
    }

    public static InsertGameEntity fromDomain(final RacingGameResult racingGameResult) {
        return new InsertGameEntity(null, racingGameResult);
    }

    public int getGameId() {
        return gameId;
    }

    public RacingGameResult getRacingGameResult() {
        return racingGameResult;
    }

    public int getCount() {
        return racingGameResult.getCount().getTargetCount();
    }
}
