package racingcar.dto;

public class CarInfoDto {
    private int racingId;
    private String name;
    private int position;
    private boolean isWinner;

    public CarInfoDto(int racingId, String name, int position, boolean isWinner) {
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

    public boolean isWinner() {
        return isWinner;
    }
}
