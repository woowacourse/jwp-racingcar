package racingcar.dto;

public class CarDto {
    private final int gameId;
    private final String name;
    private final int position;
    private final boolean isWin;

    public CarDto(int gameId, String name, int position, boolean isWin) {
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWin = isWin;
    }

    public int getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWin() {
        return isWin;
    }
}
