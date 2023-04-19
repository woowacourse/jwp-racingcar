package racingcar.dto.response;

public class RacingGameWinnersDto {
    private Long id;
    private String winners;

    public RacingGameWinnersDto(Long id, String winners) {
        this.id = id;
        this.winners = winners;
    }

    public String getWinners() {
        return winners;
    }

    public Long getId() {
        return id;
    }
}
