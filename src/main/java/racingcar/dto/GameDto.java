package racingcar.dto;

public class GameDto {

    private final int playCount;

    public GameDto(final int playCount) {
        this.playCount = playCount;
    }

    public int getPlayCount() {
        return playCount;
    }
}
