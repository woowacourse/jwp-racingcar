package racingcar.dto.response;

public class PlayerResultDto {
    private final String name;
    private final int position;

    public PlayerResultDto(String name, int position) {
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
