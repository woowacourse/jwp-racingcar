package racingcar.dto;

public class RacingGameInputDto {
    private final String names;
    private final int count;

    public RacingGameInputDto(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
