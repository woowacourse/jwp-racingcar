package racingcar.dto;

public class PlayResultDto {
    private int id;
    private String winners;

    public PlayResultDto() {

    }

    public PlayResultDto(int id, String winners) {
        this.id = id;
        this.winners = winners;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }
}
