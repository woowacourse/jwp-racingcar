package racingcar.dto;

public class CarInfo {
    private final int racingId;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public CarInfo(int racingId, String name, int position, boolean isWinner) {
        this.racingId = racingId;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public int getRacingId() {
        return racingId;
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
}
