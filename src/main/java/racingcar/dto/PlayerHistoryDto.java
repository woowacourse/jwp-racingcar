package racingcar.dto;

public class PlayerHistoryDto {

    private final String name;
    private final int position;
    private final boolean isWinner;

    private PlayerHistoryDto(final String name, final int position, final boolean isWinner) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public static PlayerHistoryDto toDto(final String name, final int position, final boolean isWinner) {
        return new PlayerHistoryDto(name, position, isWinner);
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
