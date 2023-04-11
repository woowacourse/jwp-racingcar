package racingcar.dto;

public class GameInfoDto {
    private String names;
    private int count;

    public GameInfoDto() {}

    public GameInfoDto(String names, int count) {
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
