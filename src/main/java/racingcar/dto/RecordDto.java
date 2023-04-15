package racingcar.dto;

import java.util.Objects;

public class RecordDto {

    private final int gameId;
    private final String playerName;
    private final int position;
    private final boolean isWinner;

    public RecordDto(final int gameId, final String playerName, final int position, final boolean isWinner) {
        this.gameId = gameId;
        this.playerName = playerName;
        this.position = position;
        this.isWinner = isWinner;
    }

    public int getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }

    @Override
    public String toString() {
        return "RecordDto{" +
                "gameId=" + gameId +
                ", playerName='" + playerName + '\'' +
                ", position=" + position +
                ", isWinner=" + isWinner +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordDto recordDto = (RecordDto) o;
        return gameId == recordDto.gameId && position == recordDto.position && isWinner == recordDto.isWinner && Objects.equals(playerName, recordDto.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerName, position, isWinner);
    }
}
