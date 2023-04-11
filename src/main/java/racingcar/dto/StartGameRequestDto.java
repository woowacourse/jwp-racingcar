package racingcar.dto;

public class StartGameRequestDto {

    private String names;
    private int count;

    public StartGameRequestDto() {

    }

    public StartGameRequestDto(final String names, final int count) {
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
