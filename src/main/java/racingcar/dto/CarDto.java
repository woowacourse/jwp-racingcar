package racingcar.dto;

public class CarDto {
    private final String name;
    private final int position;
    private final int gameId;

    public CarDto(final String name, final int position, final int gameId) {
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getGameId() {
        return gameId;
    }
}
