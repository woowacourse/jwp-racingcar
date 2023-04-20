package racingcar.dao.mapper;

public class PlayerDtoMapper {

    private final String name;
    private final int position;
    private final int racingGameId;

    public PlayerDtoMapper(final String name, final int position, final int racingGameId) {
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
