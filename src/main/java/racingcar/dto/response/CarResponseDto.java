package racingcar.dto.response;

public final class CarResponseDto {

    private final String name;
    private final int position;

    public CarResponseDto(final String name, final int position) {
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
