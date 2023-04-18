package racingcar.dto;

public class RacingCarDto {

    private String name;
    private int position;

    public RacingCarDto() {

    }

    public RacingCarDto(String name, int position) {
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
