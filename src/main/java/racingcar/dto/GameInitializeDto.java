package racingcar.dto;

public class GameInitializeDto {
    private String names;
    private int count;

    public GameInitializeDto() {

    }

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
