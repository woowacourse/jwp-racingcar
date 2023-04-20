package racingcar.repository.dto;

public class GetPlayerResultQueryResponseDto {

    private final long gameId;
    private final String winners;
    private final String name;
    private final int finalPosition;

    public GetPlayerResultQueryResponseDto(final long gameId, final String winners, final String name, final int finalPosition) {
        this.gameId = gameId;
        this.winners = winners;
        this.name = name;
        this.finalPosition = finalPosition;
    }

    public long getGameId() {
        return gameId;
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
