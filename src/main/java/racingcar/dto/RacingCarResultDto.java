package racingcar.dto;

import racingcar.domain.RacingCar;

public class RacingCarResultDto {
    private final String name;
    private final int position;
    private final int isWin;
    private final long gameId;

    private RacingCarResultDto(String name, int position, int isWin, long gameId) {
        this.name = name;
        this.position = position;
        this.isWin = isWin;
        this.gameId = gameId;
    }

    public static RacingCarResultDto of(RacingCar racingCar, int isWin, long gameId) {
        return new RacingCarResultDto(racingCar.getName(), racingCar.getPosition(), isWin, gameId);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int isWin() {
        return isWin;
    }

    public long getGameId() {
        return gameId;
    }
}
