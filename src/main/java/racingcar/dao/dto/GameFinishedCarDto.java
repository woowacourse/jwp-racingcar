package racingcar.dao.dto;

public class GameFinishedCarDto {
    private final String name;
    private final int position;
    private final boolean isWinner;

    public GameFinishedCarDto(final String name, final int position, final boolean isWinner) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
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
