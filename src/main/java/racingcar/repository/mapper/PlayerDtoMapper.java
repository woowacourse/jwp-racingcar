package racingcar.repository.mapper;

public class PlayerDtoMapper {

    private final int id;
    private final String name;
    private final int position;

    public PlayerDtoMapper(final int id, final String name, final int position) {
        this.id = id;
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
