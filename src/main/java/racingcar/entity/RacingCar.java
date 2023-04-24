package racingcar.entity;

public class RacingCar {

    private final long id;
    private final String name;
    private final int position;
    private final long resultId;

    public RacingCar(long id, String name, int position, long resultId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.resultId = resultId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public long getResultId() {
        return resultId;
    }
}
