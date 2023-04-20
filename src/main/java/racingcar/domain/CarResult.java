package racingcar.domain;

import java.util.Objects;

public class CarResult {
    private final Integer id;
    private final int gameResultId;
    private final String name;
    private final int position;

    public CarResult(Integer id, int gameResultId, String name, int position) {
        this.id = id;
        this.gameResultId = gameResultId;
        this.name = name;
        this.position = position;
    }

    public CarResult (int resultId, String name, int position) {
        this(null, resultId, name, position);
    }

    public Integer getId() {
        return id;
    }

    public long getGameResultId() {
        return gameResultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarResult that = (CarResult) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gameResultId);
    }
}
