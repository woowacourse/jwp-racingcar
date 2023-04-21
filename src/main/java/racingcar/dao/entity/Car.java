package racingcar.dao.entity;

public class Car {
    private final Long id;
    private final Long gameId;
    private final String name;
    private final int position;


    public Car(String name, int position) {
        this.id = null;
        this.gameId = null;
        this.name = name;
        this.position = position;
    }

    public Car(long gameId, String name, int position) {
        this.id = null;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public Long getId() {
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
