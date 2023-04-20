package racingcar.entity;

public class CarEntity {

    private final Long id;
    private final Long gameId;
    private final String name;
    private final int position;

    public CarEntity(Long id, Long gameId, String name, int position) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
