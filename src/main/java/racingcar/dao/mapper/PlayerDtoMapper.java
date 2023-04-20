package racingcar.dao.mapper;

public class PlayerDtoMapper {

    private final String name;
    private final int position;

    public PlayerDtoMapper(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
