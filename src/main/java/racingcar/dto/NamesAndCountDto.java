package racingcar.dto;

public class NamesAndCountDto {

    private String names;
    private int count;

    private NamesAndCountDto() {
    }

    public NamesAndCountDto(final String names, final int count) {
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
