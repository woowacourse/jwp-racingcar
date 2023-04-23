package racingcar.dao.dto;

public class PlayerDto {

    private final String name;
    private final int position;
    private final int racingGameId;

    public PlayerDto(final String name, final int position, final int racingGameId) {
        this.name = name;
        this.position = position;
        this.racingGameId = racingGameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getRacingGameId() {
        return racingGameId;
    }
}
