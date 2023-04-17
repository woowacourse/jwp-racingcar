package racingcar.repository.entity;

public class UsersEntity {

    private final Long id;
    private final String name;

    public UsersEntity(final String name) {
        this.id = null;
        this.name = name;
    }

    public UsersEntity(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
