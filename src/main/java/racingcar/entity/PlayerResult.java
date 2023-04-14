package racingcar.entity;

import racingcar.dto.request.PlayerResultSaveDto;

public class PlayerResult {

    private final long id;
    private final String name;
    private final int finalPosition;
    private final long gameId;

    public PlayerResult(final long id, final PlayerResultSaveDto playerResultSaveDto) {
        this.id = id;
        this.name = playerResultSaveDto.getName();
        this.finalPosition = playerResultSaveDto.getFinalPosition();
        this.gameId = playerResultSaveDto.getGameId();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public long getGameId() {
        return gameId;
    }
}
