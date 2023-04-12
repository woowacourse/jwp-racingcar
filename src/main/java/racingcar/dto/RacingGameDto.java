package racingcar.dto;

public class RacingGameDto {

    private final String winners;
    private final int count;


    public RacingGameDto(String winners, int count) {
        this.winners = winners;
        this.count = count;
    }

    public String getWinners() {
        return winners;
    }

    public int getCount() {
        return count;
    }

}
