package racingcar.domain.entity;

public class CarEntity {

    private Integer id;
    private Integer gameId;
    private final String name;
    private final Integer position;
    private final Boolean isWin;

    public CarEntity(final String name, final Integer position, final Boolean isWin) {
        this.name = name;
        this.position = position;
        this.isWin = isWin;
    }

    public CarEntity(final Integer id, final Integer gameId, final String name, final Integer position,
                     final Boolean isWin) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
        this.isWin = isWin;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public Boolean isWin() {
        return isWin;
    }
}
