package racingcar.dto.request;

public class GameRequestDto {
    private String names;
    private int count;

    public GameRequestDto() {
    }

    public GameRequestDto(String names, int count) {
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
