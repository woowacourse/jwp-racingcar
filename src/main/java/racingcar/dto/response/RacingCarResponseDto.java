package racingcar.dto.response;

public class RacingCarResponseDto {

    private String name;
    private Integer position;

    public RacingCarResponseDto(final String name, final Integer position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }
}
