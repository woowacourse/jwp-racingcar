package racingcar.dto;

import racingcar.domain.RacingCar;

public class RacingCarResultDto {
    private final String name;
    private final int position;
    private final boolean isWin;
    private final long gameId;

    public RacingCarResultDto(String name, int position, boolean isWin, long gameId) {
        this.name = name;
        this.position = position;
        this.isWin = isWin;
        this.gameId = gameId;
    }

    public static RacingCarResultDto of(RacingCar racingCar, boolean isWin, long gameId) {
        return new RacingCarResultDto(racingCar.getName(), racingCar.getPosition(), isWin, gameId);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean getIsWin() {
        return isWin;
    }

    public long getGameId() {
        return gameId;
    }
}
