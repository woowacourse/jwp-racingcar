package racingcar.dto;

public class PlayRequestDto {
    private String names;
    private String count;

    private PlayRequestDto() {
    }

    public PlayRequestDto(String names, String count) {
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
