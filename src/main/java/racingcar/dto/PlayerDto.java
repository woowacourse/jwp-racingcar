package racingcar.dto;

public class PlayerDto {

    private final String name;
    private final int position;
    private final boolean isWinner;

    public PlayerDto(final String name, final int position, final boolean isWinner) {
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
