package racingcar.repository.entity;

public class UserEntity {

    private final Long id;
    private final String name;

    public UserEntity(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UserEntity(final String name) {
        this(null, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
