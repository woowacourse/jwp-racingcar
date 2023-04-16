package racingcar.dto.request;

public class RacingCarRequestDto {

    private String names;
    private Integer count;

    public RacingCarRequestDto() {

    }

    public RacingCarRequestDto(final String names, final Integer count) {
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
