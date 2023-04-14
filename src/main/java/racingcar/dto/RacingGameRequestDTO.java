package racingcar.dto;

public class RacingGameRequestDTO {

    private String names;
    private int count;

    public RacingGameRequestDTO() {
    }

    public RacingGameRequestDTO(final String names, final int count) {
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
