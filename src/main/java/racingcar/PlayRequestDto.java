package racingcar;

public final class PlayRequestDto {
    private String names;
    private int count;

    public PlayRequestDto(final String names, final int count) {
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
