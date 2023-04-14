package racingcar;

public final class UserInputDto {

    private final String names;
    private final int count;

    public UserInputDto(final String names, final int count) {
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

