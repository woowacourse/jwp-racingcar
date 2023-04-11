package racingcar.dto;

public class RequestDto {

    private String names;
    private int count;

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

    @Override
    public String toString() {
        return "RequestDto{" +
                "names='" + names + '\'' +
                ", count=" + count +
                '}';
    }
}
