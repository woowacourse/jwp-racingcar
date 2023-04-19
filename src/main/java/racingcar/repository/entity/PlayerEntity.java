package racingcar.repository.entity;

public class PlayerEntity {

    private final long id;
    private final String name;

    public PlayerEntity(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public PlayerEntity(final String name) {
        this(0, name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
