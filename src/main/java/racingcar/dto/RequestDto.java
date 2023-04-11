package racingcar.dto;

public class RequestDto {

    private String names;
    private int count;

    public RequestDto(final String names, final int count) {
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
