package racingcar.dto;

import java.util.List;

public class PlayResultDto {
    private int id;
    private List<String> winners;

    public PlayResultDto() {

    }

    public PlayResultDto(int id, List<String> winners) {
        this.id = id;
        this.winners = winners;
    }

    public int getId() {
        return id;
    }

    public List<String> getWinners() {
        return winners;
    }
}
