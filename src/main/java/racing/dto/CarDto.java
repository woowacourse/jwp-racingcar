package racing.dto;

import racing.domain.Car;

public class CarDto {

    private final String name;
    private final int position;
    private final boolean isWinner;
    private int gameId;

    public CarDto(
        final String name,
        final int position,
        final boolean isWinner
    ) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public CarDto(final Car car) {
        this(car.getName(), car.getPosition(), car.isWinner());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setGameId(final int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }
}
