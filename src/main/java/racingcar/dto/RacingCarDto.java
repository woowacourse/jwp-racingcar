package racingcar.dto;

public class RacingCarDto {
    String name;
    int position;

    public RacingCarDto(final String name, final int position) {
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
