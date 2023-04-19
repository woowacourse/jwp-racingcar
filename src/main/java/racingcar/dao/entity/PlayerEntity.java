package racingcar.dao.entity;

public class PlayerEntity {
    private final Long id;
    private final String name;

    public PlayerEntity(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
