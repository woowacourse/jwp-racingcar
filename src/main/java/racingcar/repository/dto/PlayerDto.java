package racingcar.repository.dto;

public class PlayerDto {
    private final int id;
    private final String name;
    private final int position;
    private final int racingGameId;

    public PlayerDto(final int id, final String name, final int position, final int racingGameId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.racingGameId = racingGameId;
    }

    public int getId() {
        return id;
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
