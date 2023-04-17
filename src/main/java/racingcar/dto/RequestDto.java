package racingcar.dto;

public class RequestDto {
    private String names;
    private String count;

    private RequestDto(){
    }

    public RequestDto(String names, String count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
}
