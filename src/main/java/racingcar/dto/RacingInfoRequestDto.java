package racingcar.dto;

public class RacingInfoRequestDto {
    private final String names;
    private final int count;

    public RacingInfoRequestDto(String names, int count) {
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
