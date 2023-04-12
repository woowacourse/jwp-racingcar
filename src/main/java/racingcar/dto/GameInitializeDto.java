package racingcar.dto;

public class GameInitializeDto {
    private final String names;
    private final int count;

    public GameInitializeDto(String names, int count) {
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
