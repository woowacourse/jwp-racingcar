package racingcar.dto;

public class RacingCarResponse {
    private final String name;
    private final int position;

    public RacingCarResponse(final String name, final int position) {
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
