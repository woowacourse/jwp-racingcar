package racingcar.repository.entity;

public class UserEntity {

    private final long id;
    private final String name;

    public UserEntity(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UserEntity(final String name) {
        this(0, name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
