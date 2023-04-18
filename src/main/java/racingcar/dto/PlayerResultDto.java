package racingcar.dto;

public class PlayerResultDto {
    String name;
    int position;

    public PlayerResultDto(final String name, final int position) {
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
