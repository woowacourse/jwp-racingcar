package racingcar.dto;

public class CarResultDto {

    private int gameId;
    private final String name;
    private final int position;
    private final boolean isWin;

    public CarResultDto(final int gameId, final String name, final int position, final boolean isWin) {
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWin = isWin;
    }

    public CarResultDto(String name, int position, boolean isWin) {
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
