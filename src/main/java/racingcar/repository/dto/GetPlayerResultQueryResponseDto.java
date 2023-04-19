package racingcar.repository.dto;

public class GetPlayerResultQueryResponseDto {

    private final long id;
    private final String winners;
    private final String name;
    private final int finalPosition;

    public GetPlayerResultQueryResponseDto(final long id, final String winners, final String name, final int finalPosition) {
        this.id = id;
        this.winners = winners;
        this.name = name;
        this.finalPosition = finalPosition;
    }

    public long getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public String getName() {
        return name;
    }

    public int getFinalPosition() {
        return finalPosition;
    }
}
