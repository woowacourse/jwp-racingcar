package racingcar.entity;

public class PlayerResultEntity {
    private final int id;
    private final int resultId;
    private final String name;
    private final int position;

    public PlayerResultEntity(final int id, final int resultId, final String name, final int position) {
        this.id = id;
        this.resultId = resultId;
        this.name = name;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public int getResultId() {
        return resultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
