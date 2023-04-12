package racingcar.dto;

public class RequestDto {
    String names;
    int count;

    public RequestDto(String names, int count) {
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
