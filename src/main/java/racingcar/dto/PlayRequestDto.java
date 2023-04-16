package racingcar.dto;

public class PlayRequestDto {
    private final String names;
    private final Integer count;

    public PlayRequestDto(String names, Integer count) {
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
