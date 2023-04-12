package racingcar.dao.dto;

public class CarDto {
    private final String name;
    private final int position;
    private final boolean isWinner;
    private final int trackId;

    public CarDto(final String name, final int position, final boolean isWinner, final int trackId) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
        this.trackId = trackId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean getIsWinner() {
        return isWinner;
    }

    public int getTrackId() {
        return trackId;
    }
}
