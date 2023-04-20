package racingcar.dto.request;

public final class UserRequestDto {

    private String names;
    private Integer count;

    public UserRequestDto() {
    }

    public UserRequestDto(final String names, final Integer count) {
        this.names = names;
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}

