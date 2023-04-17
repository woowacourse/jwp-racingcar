package racingcar.domain;

import java.util.Objects;

public class CarResult {
    private final Integer id;
    private final int playResultId;
    private final String name;
    private final int position;

    private CarResult(Integer id, int playResultId, String name, int position) {
        this.id = id;
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public static CarResult of(int resultId, String name, int position) {
        return new CarResult(null, resultId, name, position);
    }

    public static CarResult of(Integer id, int resultId, String name, int position) {
        return new CarResult(id, resultId, name, position);
    }

    public Integer getId() {
        return id;
    }

    public long getPlayResultId() {
        return playResultId;
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
        return Objects.hash(id, name, playResultId);
    }
}
