package racingcar.dao.entity;

import racingcar.dto.RacingGameDto;

public class InsertGameEntity {

    private final Integer gameId;
    private final RacingGameDto racingGameDto;

    public InsertGameEntity(final Integer gameId, final RacingGameDto racingGameDto) {
        this.gameId = gameId;
        this.racingGameDto = racingGameDto;
    }

    public InsertGameEntity(final Integer gameId, final InsertGameEntity insertGameEntity) {
        this.gameId = gameId;
        racingGameDto = insertGameEntity.racingGameDto;
    }

    public static InsertGameEntity fromDomain(final RacingGameDto racingGameDto) {
        return new InsertGameEntity(null, racingGameDto);
    }

    public int getGameId() {
        return gameId;
    }

    public RacingGameDto getRacingGameResult() {
        return racingGameDto;
    }

    public int getCount() {
        return racingGameDto.getCount().getTargetCount();
    }
}
