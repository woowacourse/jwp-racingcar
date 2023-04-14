package racingcar.web.dto;

public class UserInputDto {

    private final String names;
    private final int count;

    public UserInputDto(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}

