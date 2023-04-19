package racingcar.controller;

public class RequestDto {
    private String names;
    private Integer count;

    public RequestDto() {
    }

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
