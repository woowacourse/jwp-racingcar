package racingcar.dto.request;

public final class UserRequestDto {

    private String names;
    private int count;

    public UserRequestDto() {
    }

    public UserRequestDto(final String names, final int count) {
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

