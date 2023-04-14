package racingcar.domain;

import java.util.Objects;

public class CarResult {
    private final Long id;
    private final long playResultId;
    private final String name;
    private final int position;

    private CarResult(Long id, long playResultId, String name, int position) {
        this.id = id;
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public static CarResult of(long resultId, String name, int position) {
        return new CarResult(null, resultId, name, position);
    }

    public static CarResult of(Long id, long resultId, String name, int position) {
        return new CarResult(id, resultId, name, position);
    }

    public Long getId() {
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
        if (id.equals(null) && that.id.equals(null)) {
            return Objects.equals(name, that.name) && playResultId == that.playResultId;
        }
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, playResultId);
    }
}
