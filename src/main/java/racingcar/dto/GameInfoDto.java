package racingcar.dto;

public class GameInfoDto {
    private String names;
    private String count;

    private GameInfoDto(){
    }

    public GameInfoDto(String names, String count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
}
