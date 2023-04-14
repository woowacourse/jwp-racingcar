package racingcar;

import racingcar.dto.RacingCarStatusResponse;

public class PlayerDto {

    private final long gameId;
    private final String name;
    private final int position;
    private final boolean isWinner;

    private PlayerDto(final long gameId, final String name, final int position, final boolean isWinner) {
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public static PlayerDto of(RacingCarStatusResponse response, long gameId, boolean isWinner) {
        return new PlayerDto(gameId, response.getName(), response.getPosition(), isWinner);
    }

    public long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int isWinner() {
        if(isWinner) {
            return 1;
        }
        return 0;
    }
}
