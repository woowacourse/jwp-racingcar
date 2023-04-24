package racingcar.repository.dto;

public class ResultDto {

    private final Long gameId;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public ResultDto(final Long gameId, final String name, final int position, final boolean isWinner) {
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
