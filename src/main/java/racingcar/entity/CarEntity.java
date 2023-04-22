package racingcar.entity;

public class CarEntity {
    private final Long id;
    private final Long gameId;
    private final String name;
    private final int position;

    private CarEntity(Long id, Long gameId, String name, int position) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public static CarEntity of(Long gameId, String name, int position) {
        return new CarEntity(null, gameId, name, position);
    }

    public static CarEntity of(Long id, Long gameId, String name, int position) {
        return new CarEntity(id, gameId, name, position);
    }

    public long getId() {
        return id;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
