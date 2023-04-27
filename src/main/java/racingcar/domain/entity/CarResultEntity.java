package racingcar.domain.entity;

import java.util.Objects;

public class CarResultEntity {

    private Integer id;
    private Integer gameId;
    private final String name;
    private final Integer position;
    private final Boolean isWin;

    public CarResultEntity(String name, Integer position, Boolean isWin) {
        this(null, null, name, position, isWin);
    }

    public CarResultEntity(Integer id, Integer gameId, String name, Integer position, Boolean isWin) {
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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        CarResultEntity otherCarResultEntity = (CarResultEntity) other;

        if (id == null || otherCarResultEntity.id == null) {
            return false;
        }

        return Objects.equals(gameId, otherCarResultEntity.gameId) &&
                Objects.equals(name, otherCarResultEntity.name) &&
                Objects.equals(position, otherCarResultEntity.position) &&
                Objects.equals(isWin, otherCarResultEntity.isWin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId, name, position, isWin);
    }
}
