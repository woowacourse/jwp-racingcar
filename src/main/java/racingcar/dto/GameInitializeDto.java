package racingcar.dto;

public class GameInitializeDto {
    private final String names;
    private final int count;

    public GameInitializeDto(String names, String count) {
        this.names = names;
        this.count = Integer.parseInt(count);
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
