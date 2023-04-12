package racingcar.dto;

public class RacingCarGameDto {
    private final String names;
    private final int tryCount;

    public RacingCarGameDto(String names, int tryCount) {
        this.names = names;
        this.tryCount = tryCount;
    }

    public String getNames() {
        return names;
    }

    public int getTryCount() {
        return tryCount;
    }
}
