package racingcar.controller;

public class RequestDto {
    private final String names;
    private final Integer count;

    public RequestDto(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }
}
