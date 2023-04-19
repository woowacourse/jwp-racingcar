package racingcar.service.dto;

public class GameRequestDto {
    private final String names;
    private final int count;

    public GameRequestDto(final String names, final int count) {
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
