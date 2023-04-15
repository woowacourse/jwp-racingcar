package racingcar.dto;

public class PlayerDto {

    private final String name;
    private final int position;

    public PlayerDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
