package racingcar.controller.dto;

public class RacingGameRequestDtoForPlays {

    private final String names;
    private final String count;

    public RacingGameRequestDtoForPlays(String names, String count) {
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
