package racingcar.dto.request;

public class GameRequestDto {

    private String names;
    private int count;

    public GameRequestDto(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public GameRequestDto() {
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
